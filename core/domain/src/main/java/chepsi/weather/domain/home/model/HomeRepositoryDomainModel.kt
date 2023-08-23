package chepsi.weather.domain.home.model

data class HomeRepositoryDomainModel(
    val currentTemperature: Int,
    val minimumTemperature: Int,
    val maximumTemperature: Int,
    val weather: WeatherDomainModel,
    val seaLevel: Int,
    val daysAheadForecast: List<DayAheadForecast>

)

data class DayAheadForecast(
    val day: String,
    val weather: WeatherDomainModel,
    val temperature: Int
)

sealed class WeatherDomainModel(val name:String) {
    object Cloudy : WeatherDomainModel("Cloudy")
    object Sunny : WeatherDomainModel("Sunny")
    object Rainy : WeatherDomainModel("Rainy")
    object Default : WeatherDomainModel("Other")
}
