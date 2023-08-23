package chepsi.weather.data.home.repository

import chepsi.weather.data.home.mappers.ApiToDataModelMapper.toData
import chepsi.weather.data.home.mappers.DataToDomainModelMapper.toDomain
import chepsi.weather.domain.home.model.HomeRepositoryDomainModel
import chepsi.weather.domain.home.repository.HomeRepository
import chepsi.weather.local_data_source.location.LocationSource
import chepsi.weather.remote_data_source.api.WeatherRemoteSource
import chepsi.weather.remote_data_source.models.LocationRequestModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf

class HomeDataRepository @Inject constructor(
    private val weatherRemoteSource: WeatherRemoteSource,
    private val locationSource: LocationSource
) : HomeRepository {
    override suspend fun fetchHomeInformation(): Flow<HomeRepositoryDomainModel> {
        val currentLocation = locationSource.fetchCurrentLocation().first()
        val locationRequestModel = LocationRequestModel(
            latitude = currentLocation.latitude,
            longitude = currentLocation.longitude
        )
        return combine(
            flow = flowOf(weatherRemoteSource.fetchCurrentLocationWeather(locationRequestModel)),
            flow2 = flowOf(weatherRemoteSource.fetchDaysAheadWeather(locationRequestModel))
        ) { currentWeatherApiModel, forecastApiModel ->
            val dataModel = currentWeatherApiModel.toData(forecastApiModel)
            dataModel.toDomain()
        }
    }
}
