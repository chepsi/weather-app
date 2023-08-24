package chepsi.weather.presentation.screens.main.model

import androidx.annotation.DrawableRes

data class DayForecast(
    val day: String,
    val name: String,
    val temperature: String,
    @DrawableRes val icon: Int
)
