package chepsi.weather.data.home.repository

import chepsi.weather.domain.home.model.DayAheadForecast
import chepsi.weather.domain.home.model.HomeRepositoryDomainModel
import chepsi.weather.domain.home.model.WeatherDomainModel
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
            HomeRepositoryDomainModel(
                currentTemperature = currentWeatherApiModel.main?.temp?.toInt() ?: 0,
                minimumTemperature = currentWeatherApiModel.main?.tempMin?.toInt() ?: 0,
                maximumTemperature = currentWeatherApiModel.main?.tempMax?.toInt() ?: 0,
                weather = when (currentWeatherApiModel.weather?.firstOrNull()?.main) {
                    "Clouds" -> WeatherDomainModel.Cloudy
                    "Rain" -> WeatherDomainModel.Rainy
                    "Clear" -> WeatherDomainModel.Sunny
                    else -> WeatherDomainModel.Default
                },
                daysAheadForecast = forecastApiModel.list?.map {
                    DayAheadForecast(
                        day = it.dtTxt.orEmpty(),
                        weather = when (currentWeatherApiModel.weather?.firstOrNull()?.main) {
                            "Clouds" -> WeatherDomainModel.Cloudy
                            "Rain" -> WeatherDomainModel.Rainy
                            "Clear" -> WeatherDomainModel.Sunny
                            else -> WeatherDomainModel.Default
                        },
                        temperature = it.main?.temp?.toInt() ?: 0
                    )
                } ?: emptyList(),
                seaLevel = 1000
            )
        }
    }
}
