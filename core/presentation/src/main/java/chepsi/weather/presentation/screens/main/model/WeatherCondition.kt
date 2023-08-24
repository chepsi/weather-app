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
    @DrawableRes val forecastDrawable: Int,
    val backgroundColor: Color
) {
    object ForestCloudy : WeatherCondition(
        name = "Cloudy",
        image = R.drawable.img_forest_cloudy,
        backgroundColor = ColorCloudy,
        forecastDrawable = R.drawable.img_partly_sunny
    )

    object ForestSunny : WeatherCondition(
        name = "Sunny",
        image = R.drawable.img_forest_sunny,
        backgroundColor = ColorSunny,
        forecastDrawable = R.drawable.img_clear
    )

    object ForestRainy : WeatherCondition(
        name = "Rainy",
        image = R.drawable.imh_forest_rainy,
        backgroundColor = ColorRainy,
        forecastDrawable = R.drawable.img_rainy
    )

    object SeaCloudy : WeatherCondition(
        name = "Cloudy",
        image = R.drawable.img_sea_cloudy,
        backgroundColor = ColorCloudy,
        forecastDrawable = R.drawable.img_partly_sunny
    )

    object SeaSunny : WeatherCondition(
        name = "Sunny",
        image = R.drawable.img_sea_sunny,
        backgroundColor = ColorSunny,
        forecastDrawable = R.drawable.img_clear
    )

    object SeaRainy : WeatherCondition(
        name = "Rainy",
        image = R.drawable.img_sea_rainy,
        backgroundColor = ColorRainy,
        forecastDrawable = R.drawable.img_rainy
    )
}
