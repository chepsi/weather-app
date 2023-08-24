package chepsi.weather.presentation.screens.main.mapper

import chepsi.weather.domain.home.model.DayAheadForecast
import chepsi.weather.domain.home.model.WeatherDomainModel
import chepsi.weather.presentation.screens.main.mapper.DateMapper.toDayOfWeek
import chepsi.weather.presentation.screens.main.model.DayForecast
import chepsi.weather.presentation.screens.main.model.WeatherCondition

object DomainToPresentationMappers {

    fun WeatherDomainModel.toPresentation() = when (this) {
        WeatherDomainModel.Cloudy -> WeatherCondition.ForestCloudy
        WeatherDomainModel.Sunny, WeatherDomainModel.Default -> WeatherCondition.ForestSunny
        WeatherDomainModel.Rainy -> WeatherCondition.ForestRainy
    }

    fun List<DayAheadForecast>.toPresentation() = map { forecast ->
        DayForecast(
            day = forecast.day.toDayOfWeek(),
            name = forecast.weather.name,
            temperature = forecast.temperature.toTemperatureString(),
            weatherCondition = forecast.weather.toPresentation()
        )
    }

    fun Int.toTemperatureString() = "$this °"
}