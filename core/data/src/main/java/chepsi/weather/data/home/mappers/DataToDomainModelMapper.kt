package chepsi.weather.data.home.mappers

import chepsi.weather.data.home.model.ForecastDataModel
import chepsi.weather.domain.home.model.ForecastDomainModel
import chepsi.weather.domain.home.model.HomeRepositoryDomainModel
import chepsi.weather.domain.home.model.WeatherDomainModel

object DataToDomainModelMapper {
    fun ForecastDataModel.toDomain() = HomeRepositoryDomainModel(
        currentTemperature = currentTemperature.toInt(),
        minimumTemperature = minimumTemperature.toInt(),
        maximumTemperature = maximumTemperature.toInt(),
        weather = weather.toWeatherConditionDomainMapper(),
        daysAheadForecast = cityForecast.map {
            ForecastDomainModel(
                day = it.date,
                weather = it.weather.toWeatherConditionDomainMapper(),
                temperature = it.temperature.toInt()
            )
        }.filter { it.day.contains("12:00") }.distinctBy { it.day },
        seaLevel = 1000
    )

    fun String.toWeatherConditionDomainMapper() = when (this) {
        "Clouds" -> WeatherDomainModel.Cloudy
        "Rain" -> WeatherDomainModel.Rainy
        "Clear" -> WeatherDomainModel.Sunny
        else -> WeatherDomainModel.Default
    }
}
