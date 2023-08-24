package chepsi.weather.data.home.mappers

import chepsi.weather.data.home.model.ForecastDataModel
import chepsi.weather.localdatasource.city.model.CityEntity
import chepsi.weather.localdatasource.weather.model.ForecastEntity
import chepsi.weather.localdatasource.weather.model.WeatherLocalSourceEntity

object DataToDatabaseModelMapper {
    fun ForecastDataModel.toWeatherEntity(currentTime: Long) = WeatherLocalSourceEntity(
        cityId = cityId,
        cityName = cityName,
        seaLevel = seaLevel,
        createdAt = currentTime,
        maximumTemperature = maximumTemperature,
        minimumTemperature = minimumTemperature,
        temperature = currentTemperature,
        weather = weather
    )

    fun ForecastDataModel.toCityEntity() = CityEntity(
        cityId = cityId,
        cityName = cityName,
        isCurrent = true,
        latitude = coordinates.latitude,
        longitude = coordinates.longitude
    )

    fun ForecastDataModel.toForecastEntity() = cityForecast.map { forecast ->
        ForecastEntity(
            minimumTemperature = forecast.minimumTemperature,
            maximumTemperature = forecast.maximumTemperature,
            temperature = forecast.temperature,
            date = forecast.date,
            weather = forecast.weather,
            cityId = cityId
        )
    }
}
