package chepsi.weather.presentation.screens.main.model

import chepsi.weather.presentation.screens.main.model.WeatherCondition.ForestRainy

data class MainScreenState(
    val currentTemperature: String = "",
    val minimumTemperature: String = "",
    val maximumTemperature: String = "",
    val currentWeather: WeatherCondition = ForestRainy,
    val daysForecast: List<DayForecast> = emptyList()
)
