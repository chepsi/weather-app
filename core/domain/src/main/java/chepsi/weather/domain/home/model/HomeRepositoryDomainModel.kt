package chepsi.weather.domain.home.model

data class HomeRepositoryDomainModel(
    val currentTemperature: Int,
    val minimumTemperature: Int,
    val maximumTemperature: Int,
    val weather: WeatherDomainModel,
    val seaLevel: Int,
    val daysAheadForecast: List<ForecastDomainModel>,
    val cityName: String
)
