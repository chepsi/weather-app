package chepsi.weather.local_data_source.weather.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import chepsi.weather.local_data_source.weather.model.WeatherLocalSourceEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class WeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insert(data: WeatherLocalSourceEntity)

    @Query("SELECT * FROM weather")
    abstract fun getAll(): Flow<WeatherLocalSourceEntity>
}
