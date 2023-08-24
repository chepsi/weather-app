package chepsi.weather.localdatasource.city.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import chepsi.weather.localdatasource.city.model.CityEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class CityDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insert(data: CityEntity)

    @Query("SELECT * FROM city")
    abstract fun getAll(): Flow<CityEntity>
}
