package chepsi.weather.presentation.screens.main.model

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import chepsi.weather.presentation.R
import chepsi.weather.presentation.theme.ColorCloudy
import chepsi.weather.presentation.theme.ColorRainy
import chepsi.weather.presentation.theme.ColorSunny

sealed class WeatherCondition(
    val name: String,
    @DrawableRes val image: Int,
    val backgroundColor: Color
) {
    object ForestCloudy : WeatherCondition(
        name = "Cloudy",
        image = R.drawable.img_forest_cloudy,
        backgroundColor = ColorCloudy
    )

    object ForestSunny : WeatherCondition(
        name = "Sunny",
        image = R.drawable.img_forest_sunny,
        backgroundColor = ColorSunny
    )

    object ForestRainy : WeatherCondition(
        name = "Rainy",
        image = R.drawable.imh_forest_rainy,
        backgroundColor = ColorRainy
    )

    object SeaCloudy : WeatherCondition(
        name = "Cloudy",
        image = R.drawable.img_sea_cloudy,
        backgroundColor = ColorCloudy
    )

    object SeaSunny : WeatherCondition(
        name = "Sunny",
        image = R.drawable.img_sea_sunny,
        backgroundColor = ColorSunny
    )

    object SeaRainy : WeatherCondition(
        name = "Rainy",
        image = R.drawable.img_sea_rainy,
        backgroundColor = ColorRainy
    )
}
