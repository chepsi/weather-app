package chepsi.weather.remote_data_source.api

import chepsi.weather.remote_data_source.models.CurrentWeatherApiModel
import chepsi.weather.remote_data_source.models.ForecastApiModel
import chepsi.weather.remote_data_source.models.LocationRequestModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import javax.inject.Inject

class WeatherRemoteSourceImpl @Inject constructor(private val httpClient: HttpClient) :
    WeatherRemoteSource {
    override suspend fun fetchCurrentLocationWeather(request: LocationRequestModel): CurrentWeatherApiModel {
        return httpClient.get("https://api.openweathermap.org/data/2.5/weather") {
            parameter("lat", request.latitude)
            parameter("lon", request.longitude)
            parameter("appid", "d835c2de50ed118ee0e418d16a8cddb1")
            parameter("units", "metric")
        }.body()
    }

    override suspend fun fetchDaysAheadWeather(request: LocationRequestModel): ForecastApiModel {
        return httpClient.get("https://api.openweathermap.org/data/2.5/forecast") {
            parameter("lat", request.latitude)
            parameter("lon", request.longitude)
            parameter("appid", "d835c2de50ed118ee0e418d16a8cddb1")
            parameter("units", "metric")
        }.body()
    }
}
