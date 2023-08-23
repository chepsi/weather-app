package chepsi.weather.local_data_source.database

import androidx.room.Database
import androidx.room.RoomDatabase
import chepsi.weather.local_data_source.city.dao.CityDao
import chepsi.weather.local_data_source.city.model.CityEntity
import chepsi.weather.local_data_source.weather.dao.ForecastDao
import chepsi.weather.local_data_source.weather.dao.WeatherDao
import chepsi.weather.local_data_source.weather.model.ForecastEntity
import chepsi.weather.local_data_source.weather.model.WeatherLocalSourceEntity

@Database(
    entities = [ForecastEntity::class, CityEntity::class, WeatherLocalSourceEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
    abstract fun cityDao(): CityDao

    abstract fun forecastDao(): ForecastDao
}
