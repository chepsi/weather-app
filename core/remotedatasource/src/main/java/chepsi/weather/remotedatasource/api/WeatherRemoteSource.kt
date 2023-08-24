package chepsi.weather.remotedatasource.api

import chepsi.weather.remotedatasource.models.ForecastApiModel
import chepsi.weather.remotedatasource.models.LocationRequestModel
import chepsi.weather.remotedatasource.models.WeatherApiModel

interface WeatherRemoteSource {
    suspend fun fetchWeather(request: LocationRequestModel): WeatherApiModel
    suspend fun fetchForecast(request: LocationRequestModel): ForecastApiModel
}
