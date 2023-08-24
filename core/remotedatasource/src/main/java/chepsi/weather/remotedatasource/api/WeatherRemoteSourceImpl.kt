package chepsi.weather.remotedatasource.api

import chepsi.weather.remotedatasource.models.ForecastApiModel
import chepsi.weather.remotedatasource.models.LocationRequestModel
import chepsi.weather.remotedatasource.models.WeatherApiModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import javax.inject.Inject

class WeatherRemoteSourceImpl @Inject constructor(private val httpClient: HttpClient) :
    WeatherRemoteSource {
    override suspend fun fetchWeather(request: LocationRequestModel): WeatherApiModel =
        httpClient.get("https://api.openweathermap.org/data/2.5/weather") {
            parameter("lat", request.latitude)
            parameter("lon", request.longitude)
            parameter("appid", "d835c2de50ed118ee0e418d16a8cddb1")
            parameter("units", "metric")
        }.body()

    override suspend fun fetchForecast(request: LocationRequestModel): ForecastApiModel =
        httpClient.get("https://api.openweathermap.org/data/2.5/forecast") {
            parameter("lat", request.latitude)
            parameter("lon", request.longitude)
            parameter("appid", "d835c2de50ed118ee0e418d16a8cddb1")
            parameter("units", "metric")
        }.body()
}
