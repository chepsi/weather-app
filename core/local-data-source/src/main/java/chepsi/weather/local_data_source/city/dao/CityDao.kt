package chepsi.weather.local_data_source.city.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import chepsi.weather.local_data_source.city.model.CityEntity

@Dao
abstract class CityDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insert(data: CityEntity)
}
