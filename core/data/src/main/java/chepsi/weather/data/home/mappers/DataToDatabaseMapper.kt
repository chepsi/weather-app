package chepsi.weather.data.home.mappers

import chepsi.weather.data.home.model.ForecastDataModel
import chepsi.weather.local_data_source.city.model.CityEntity
import chepsi.weather.local_data_source.weather.model.ForecastEntity
import chepsi.weather.local_data_source.weather.model.WeatherLocalSourceEntity

object DataToDatabaseMapper {
    fun ForecastDataModel.toWeatherEntity() = WeatherLocalSourceEntity(
        cityId = cityId,
        cityName = cityName,
        seaLevel = seaLevel,
        createdAt = System.currentTimeMillis(),
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
