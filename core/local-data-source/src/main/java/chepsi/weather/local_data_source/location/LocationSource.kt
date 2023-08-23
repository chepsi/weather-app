package chepsi.weather.local_data_source.location

import chepsi.weather.local_data_source.location.model.LocationDataSourceModel
import kotlinx.coroutines.flow.Flow

interface LocationSource {
    suspend fun fetchCurrentLocation(): Flow<LocationDataSourceModel>
}