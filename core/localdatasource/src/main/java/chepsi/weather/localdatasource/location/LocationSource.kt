package chepsi.weather.localdatasource.location

import chepsi.weather.localdatasource.location.model.LocationDataSourceModel
import kotlinx.coroutines.flow.Flow

interface LocationSource {
    suspend fun fetchCurrentLocation(): Flow<LocationDataSourceModel>
}
