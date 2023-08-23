package chepsi.weather.data.home.mappers

import chepsi.weather.data.home.model.ForecastDataModel
import chepsi.weather.domain.home.model.DayAheadForecast
import chepsi.weather.domain.home.model.HomeRepositoryDomainModel
import chepsi.weather.domain.home.model.WeatherDomainModel

object DataToDomainModelMapper {
    fun ForecastDataModel.toDomain() = HomeRepositoryDomainModel(
        currentTemperature = currentTemperature.toInt(),
        minimumTemperature = minimumTemperature.toInt(),
        maximumTemperature = maximumTemperature.toInt(),
        weather = weather.toWeatherConditionDomainMapper(),
        daysAheadForecast = cityForecast.map {
            DayAheadForecast(
                day = it.date,
                weather = weather.toWeatherConditionDomainMapper(),
                temperature = it.temperature.toInt()
            )
        },
        seaLevel = 1000
    )

    fun String.toWeatherConditionDomainMapper() = when (this) {
        "Clouds" -> WeatherDomainModel.Cloudy
        "Rain" -> WeatherDomainModel.Rainy
        "Clear" -> WeatherDomainModel.Sunny
        else -> WeatherDomainModel.Default
    }
}
