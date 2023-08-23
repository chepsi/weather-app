package chepsi.weather.local_data_source.weather.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import chepsi.weather.local_data_source.weather.model.WeatherLocalSourceEntity

@Dao
abstract class WeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insert(data: WeatherLocalSourceEntity)
}
