package chepsi.weather.presentation.screens.main.model

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import chepsi.weather.presentation.R
import chepsi.weather.presentation.theme.ColorCloudy
import chepsi.weather.presentation.theme.ColorRainy
import chepsi.weather.presentation.theme.ColorSunny

sealed class WeatherPresentationModel(
    val name: String,
    @DrawableRes val image: Int,
    @DrawableRes val forecastDrawable: Int,
    val backgroundColor: Color
) {
    object ForestCloudy : WeatherPresentationModel(
        name = "Cloudy",
        image = R.drawable.img_forest_cloudy,
        backgroundColor = ColorCloudy,
        forecastDrawable = R.drawable.img_partly_sunny
    )

    object ForestSunny : WeatherPresentationModel(
        name = "Sunny",
        image = R.drawable.img_forest_sunny,
        backgroundColor = ColorSunny,
        forecastDrawable = R.drawable.img_clear
    )

    object ForestRainy : WeatherPresentationModel(
        name = "Rainy",
        image = R.drawable.imh_forest_rainy,
        backgroundColor = ColorRainy,
        forecastDrawable = R.drawable.img_rainy
    )

    object SeaCloudy : WeatherPresentationModel(
        name = "Cloudy",
        image = R.drawable.img_sea_cloudy,
        backgroundColor = ColorCloudy,
        forecastDrawable = R.drawable.img_partly_sunny
    )

    object SeaSunny : WeatherPresentationModel(
        name = "Sunny",
        image = R.drawable.img_sea_sunny,
        backgroundColor = ColorSunny,
        forecastDrawable = R.drawable.img_clear
    )

    object SeaRainy : WeatherPresentationModel(
        name = "Rainy",
        image = R.drawable.img_sea_rainy,
        backgroundColor = ColorRainy,
        forecastDrawable = R.drawable.img_rainy
    )
}
