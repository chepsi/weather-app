package chepsi.weather.data.home.model

data class ForecastDataModel(
    val cityName: String,
    val cityId: String,
    val cityForecast: List<CityForecastDataModel>,
    val seaLevel: Int,
    val weather: String,
    val coordinates: CityCoordinatesDataModel,
    val minimumTemperature: Double,
    val maximumTemperature: Double,
    val currentTemperature: Double
)

data class CityForecastDataModel(
    val date: String,
    val temperature: Double,
    val minimumTemperature: Double,
    val maximumTemperature: Double,
    val weather: String
)

data class CityCoordinatesDataModel(
    val latitude: Double,
    val longitude: Double
)