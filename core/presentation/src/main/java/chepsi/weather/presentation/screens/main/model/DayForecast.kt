package chepsi.weather.presentation.screens.main.model

data class DayForecast(
    val day: String,
    val name: String,
    val temperature: String,
    val weatherCondition: WeatherCondition
)
