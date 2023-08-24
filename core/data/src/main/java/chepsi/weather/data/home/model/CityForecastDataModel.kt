package chepsi.weather.data.home.model

data class CityForecastDataModel(
    val date: String,
    val temperature: Double,
    val minimumTemperature: Double,
    val maximumTemperature: Double,
    val weather: String
)
