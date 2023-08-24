package chepsi.weather.localdatasource.weather.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import chepsi.weather.localdatasource.weather.model.ForecastEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class ForecastDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insert(data: List<ForecastEntity>)

    @Query("SELECT * FROM forecast")
    abstract fun getAll(): Flow<List<ForecastEntity>>
}
