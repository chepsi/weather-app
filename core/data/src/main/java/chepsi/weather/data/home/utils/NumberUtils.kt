package chepsi.weather.data.home.utils

object NumberUtils {
    fun Double?.orZero() = this ?: 0.0
}