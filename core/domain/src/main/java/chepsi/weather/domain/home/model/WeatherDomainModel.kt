package chepsi.weather.domain.home.model

sealed class WeatherDomainModel(val name: String) {
    object Cloudy : WeatherDomainModel("Cloudy")
    object Sunny : WeatherDomainModel("Sunny")
    object Rainy : WeatherDomainModel("Rainy")
    object Default : WeatherDomainModel("Other")
}
