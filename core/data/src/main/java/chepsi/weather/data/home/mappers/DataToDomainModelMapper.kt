package chepsi.weather.data.home.mappers

import chepsi.weather.data.home.model.ForecastDataModel
import chepsi.weather.data.home.utils.DateAndTimeUtils
import chepsi.weather.domain.home.model.ForecastDomainModel
import chepsi.weather.domain.home.model.HomeRepositoryDomainModel
import chepsi.weather.domain.home.model.WeatherDomainModel

class DataToDomainModelMapper(private val dateAndTimeUtils: DateAndTimeUtils) {
    fun toDomain(forecastDataModel: ForecastDataModel) = HomeRepositoryDomainModel(
        currentTemperature = forecastDataModel.currentTemperature.toInt(),
        minimumTemperature = forecastDataModel.minimumTemperature.toInt(),
        maximumTemperature = forecastDataModel.maximumTemperature.toInt(),
        weather = weatherConditionDomainMapper(forecastDataModel.weather),
        daysAheadForecast = forecastDataModel.cityForecast.map {
            ForecastDomainModel(
                day = it.date,
                weather = weatherConditionDomainMapper(it.weather),
                temperature = it.temperature.toInt()
            )
        }
            .distinctBy { it.day }
            .filter { it.day.isDayInFuture() }
            .filter { it.day.contains("12:00") },
        seaLevel = forecastDataModel.seaLevel,
        cityName = forecastDataModel.cityName
    )

    private fun String.isDayInFuture(): Boolean {
        val today = dateAndTimeUtils.todayInMillis()
        val forecastDay = dateAndTimeUtils.convertToLong(this)
        return today <= forecastDay
    }

    fun weatherConditionDomainMapper(weatherCondition: String) = when (weatherCondition) {
        "Clouds" -> WeatherDomainModel.Cloudy
        "Rain" -> WeatherDomainModel.Rainy
        "Clear" -> WeatherDomainModel.Sunny
        else -> WeatherDomainModel.Default
    }
}
