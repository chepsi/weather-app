package chepsi.weather.data.home.model

data class ForecastDataModel(
    val cityId: Int,
    val cityName: String,
    val cityForecast: List<CityForecastDataModel>,
    val seaLevel: Int,
    val weather: String,
    val coordinates: CityCoordinatesDataModel,
    val minimumTemperature: Double,
    val maximumTemperature: Double,
    val currentTemperature: Double
)
