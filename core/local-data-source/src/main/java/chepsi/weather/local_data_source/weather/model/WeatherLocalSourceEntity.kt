package chepsi.weather.local_data_source.weather.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weather")
data class WeatherLocalSourceEntity(
    @PrimaryKey val cityId: Int,
    @ColumnInfo(name = "city_name") val cityName: String,
    @ColumnInfo(name = "sea_level") val seaLevel: Int,
    @ColumnInfo(name = "weather") val weather: String,
    @ColumnInfo(name = "minimum_temperature") val minimumTemperature: Double,
    @ColumnInfo(name = "maximum_temperature") val maximumTemperature: Double,
    @ColumnInfo(name = "temperature") val temperature: Double,
    @ColumnInfo(name = "created_at") val createdAt: Long
)
