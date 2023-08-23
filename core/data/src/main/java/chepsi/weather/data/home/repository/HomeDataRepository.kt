package chepsi.weather.data.home.repository

import chepsi.weather.data.home.mappers.ApiToDataModelMapper.toData
import chepsi.weather.data.home.mappers.DataToDomainModelMapper.toDomain
import chepsi.weather.domain.home.model.HomeRepositoryDomainModel
import chepsi.weather.domain.home.repository.HomeRepository
import chepsi.weather.remote_data_source.api.WeatherRemoteSource
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOf

class HomeDataRepository @Inject constructor(
    private val weatherRemoteSource: WeatherRemoteSource
) : HomeRepository {
    override suspend fun fetchHomeInformation(): Flow<HomeRepositoryDomainModel> {
        return combine(
            flow = flowOf(weatherRemoteSource.fetchCurrentLocationWeather()),
            flow2 = flowOf(weatherRemoteSource.fetchDaysAheadWeather())
        ) { currentWeatherApiModel, forecastApiModel ->
            val dataModel = currentWeatherApiModel.toData(forecastApiModel)
            dataModel.toDomain()
        }
    }
}
