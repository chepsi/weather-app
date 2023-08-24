package chepsi.weather.presentation.screens.main.mapper

import chepsi.weather.domain.home.model.ForecastDomainModel
import chepsi.weather.domain.home.model.WeatherDomainModel
import chepsi.weather.presentation.screens.main.mapper.DateMapper.toDayOfWeek
import chepsi.weather.presentation.screens.main.model.DayForecast
import chepsi.weather.presentation.screens.main.model.WeatherPresentationModel

object DomainToPresentationMappers {

    fun WeatherDomainModel.toPresentation() = when (this) {
        WeatherDomainModel.Cloudy -> WeatherPresentationModel.ForestCloudy
        WeatherDomainModel.Sunny, WeatherDomainModel.Default -> WeatherPresentationModel.ForestSunny
        WeatherDomainModel.Rainy -> WeatherPresentationModel.ForestRainy
    }

    fun List<ForecastDomainModel>.toPresentation() = map { forecast ->
        DayForecast(
            day = forecast.day.toDayOfWeek(),
            temperature = forecast.temperature.toTemperatureString(),
            weather = forecast.weather.toPresentation()
        )
    }

    fun Int.toTemperatureString() = "$this °"
}
