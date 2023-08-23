package chepsi.weather.local_data_source.weather.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "forecast")
data class ForecastEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo("forecast_date") val date: String,
    @ColumnInfo(name = "minimum_temperature") val minimumTemperature: Double,
    @ColumnInfo(name = "maximum_temperature") val maximumTemperature: Double,
    @ColumnInfo(name = "temperature") val temperature: Double,
    @ColumnInfo(name = "weather") val weather: String,
    @ColumnInfo(name = "city_id") val cityId: Int,
)
