package chepsi.weather.remote_data_source.api

import chepsi.weather.remote_data_source.models.CurrentWeatherApiModel
import chepsi.weather.remote_data_source.models.ForecastApiModel
import chepsi.weather.remote_data_source.models.LocationRequestModel

interface WeatherRemoteSource {
    suspend fun fetchCurrentLocationWeather(request: LocationRequestModel): CurrentWeatherApiModel
    suspend fun fetchDaysAheadWeather(request: LocationRequestModel): ForecastApiModel
}
