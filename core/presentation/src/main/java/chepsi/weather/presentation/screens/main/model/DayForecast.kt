package chepsi.weather.presentation.screens.main.model

data class DayForecast(
    val day: String,
    val temperature: String,
    val weather: WeatherPresentationModel
)
