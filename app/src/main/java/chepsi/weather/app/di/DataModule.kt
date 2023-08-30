package chepsi.weather.app.di

import chepsi.weather.data.home.mappers.DataToDomainModelMapper
import chepsi.weather.data.home.repository.HomeDataRepository
import chepsi.weather.data.home.utils.DateAndTimeUtils
import chepsi.weather.domain.home.repository.HomeRepository
import chepsi.weather.localdatasource.city.dao.CityDao
import chepsi.weather.localdatasource.location.LocationSource
import chepsi.weather.localdatasource.weather.dao.ForecastDao
import chepsi.weather.localdatasource.weather.dao.WeatherDao
import chepsi.weather.remotedatasource.api.WeatherRemoteSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    @Singleton
    fun provideHomeRepository(
        weatherRemoteSource: WeatherRemoteSource,
        locationSource: LocationSource,
        weatherDao: WeatherDao,
        forecastDao: ForecastDao,
        cityDao: CityDao,
        dateAndTimeUtils: DateAndTimeUtils,
        dataToDomainModelMapper: DataToDomainModelMapper
    ): HomeRepository = HomeDataRepository(
        weatherRemoteSource,
        locationSource,
        weatherDao,
        forecastDao,
        cityDao,
        dateAndTimeUtils,
        dataToDomainModelMapper
    )

    @Provides
    fun providesDateAndTimeUtils() = DateAndTimeUtils()

    @Provides
    fun provides(dateAndTimeUtils: DateAndTimeUtils) = DataToDomainModelMapper(dateAndTimeUtils)
}
