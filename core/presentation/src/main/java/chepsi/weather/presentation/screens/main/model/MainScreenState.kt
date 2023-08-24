package chepsi.weather.presentation.screens.main.model

import chepsi.weather.presentation.screens.main.model.WeatherPresentationModel.ForestRainy

data class MainScreenState(
    val currentTemperature: String = "",
    val minimumTemperature: String = "",
    val maximumTemperature: String = "",
    val currentWeather: WeatherPresentationModel = ForestRainy,
    val daysForecast: List<DayForecast> = emptyList(),
    val cityName: String = ""
)
