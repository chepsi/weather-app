package chepsi.weather.data.home.repository

import chepsi.weather.domain.home.model.DayAheadForecast
import chepsi.weather.domain.home.model.HomeRepositoryDomainModel
import chepsi.weather.domain.home.model.WeatherDomainModel
import chepsi.weather.domain.home.repository.HomeRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class HomeDataRepository @Inject constructor() : HomeRepository {
    override suspend fun fetchHomeInformation(): Flow<HomeRepositoryDomainModel> {
        return flow {
            val daysAheadForecast = listOf(
                DayAheadForecast(day = "Monday", weather = WeatherDomainModel.Cloudy, 20),
                DayAheadForecast(day = "Tuesday", weather = WeatherDomainModel.Sunny, 19),
                DayAheadForecast(day = "Wednesday", weather = WeatherDomainModel.Rainy, 18),
                DayAheadForecast(day = "Thursday", weather = WeatherDomainModel.Cloudy, 19),
                DayAheadForecast(day = "Friday", weather = WeatherDomainModel.Cloudy, 20)
            )
            val forecastData = HomeRepositoryDomainModel(
                currentTemperature = 18,
                minimumTemperature = 16,
                maximumTemperature = 22,
                weather = WeatherDomainModel.Cloudy,
                daysAheadForecast = daysAheadForecast,
                seaLevel = 1000
            )
            emit(forecastData)
        }
    }
}
