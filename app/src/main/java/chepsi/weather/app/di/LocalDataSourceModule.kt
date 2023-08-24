package chepsi.weather.app.di

import android.content.Context
import androidx.room.Room
import chepsi.weather.localdatasource.city.dao.CityDao
import chepsi.weather.localdatasource.database.AppDatabase
import chepsi.weather.localdatasource.location.LocationDataSource
import chepsi.weather.localdatasource.location.LocationSource
import chepsi.weather.localdatasource.weather.dao.ForecastDao
import chepsi.weather.localdatasource.weather.dao.WeatherDao
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.Granularity
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

private const val TIME_INTERVAL_IN_MILLIS = 1000L
private const val MINIMAL_DISTANCE = 10000f
private const val DATABASE_NAME = "weather-app-db"

@Module
@InstallIn(SingletonComponent::class)
object LocalDataSourceModule {

    @Provides
    fun provideFusedLocationProvider(context: Context) =
        LocationServices.getFusedLocationProviderClient(context)

    @Provides
    fun providesContext(@ApplicationContext context: Context): Context = context

    @Provides
    fun providesLocationRequest() =
        LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, TIME_INTERVAL_IN_MILLIS).apply {
            setMinUpdateDistanceMeters(MINIMAL_DISTANCE)
            setGranularity(Granularity.GRANULARITY_PERMISSION_LEVEL)
            setWaitForAccurateLocation(true)
        }.build()

    @Provides
    fun providesLocationDataSource(
        locationRequest: LocationRequest,
        fusedLocationProviderClient: FusedLocationProviderClient
    ): LocationSource = LocationDataSource(fusedLocationProviderClient, locationRequest)

    @Provides
    @Singleton
    fun providesDatabase(
        context: Context
    ): AppDatabase = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        DATABASE_NAME
    ).build()

    @Provides
    fun providesWeatherDao(database: AppDatabase): WeatherDao = database.weatherDao()

    @Provides
    fun providesCityDao(database: AppDatabase): CityDao = database.cityDao()

    @Provides
    fun providesForecastDao(database: AppDatabase): ForecastDao = database.forecastDao()
}
