package chepsi.weather.localdatasource.database

import androidx.room.Database
import androidx.room.RoomDatabase
import chepsi.weather.localdatasource.city.dao.CityDao
import chepsi.weather.localdatasource.city.model.CityEntity
import chepsi.weather.localdatasource.weather.dao.ForecastDao
import chepsi.weather.localdatasource.weather.dao.WeatherDao
import chepsi.weather.localdatasource.weather.model.ForecastEntity
import chepsi.weather.localdatasource.weather.model.WeatherLocalSourceEntity

@Database(
    entities = [ForecastEntity::class, CityEntity::class, WeatherLocalSourceEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
    abstract fun cityDao(): CityDao

    abstract fun forecastDao(): ForecastDao
}
