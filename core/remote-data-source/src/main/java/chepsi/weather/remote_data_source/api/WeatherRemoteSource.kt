package chepsi.weather.remote_data_source.api

import chepsi.weather.remote_data_source.models.CurrentWeatherApiModel
import chepsi.weather.remote_data_source.models.ForecastApiModel

interface WeatherRemoteSource {
    suspend fun fetchCurrentLocationWeather(): CurrentWeatherApiModel
    suspend fun fetchDaysAheadWeather(): ForecastApiModel
}
