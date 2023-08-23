package chepsi.weather.data.home.mappers

import chepsi.weather.data.home.model.CityCoordinatesDataModel
import chepsi.weather.data.home.model.CityForecastDataModel
import chepsi.weather.data.home.model.ForecastDataModel
import chepsi.weather.data.home.utils.NumberUtils.orZero
import chepsi.weather.remote_data_source.models.CurrentWeatherApiModel
import chepsi.weather.remote_data_source.models.ForecastApiModel

object ApiToDataModelMapper {
    fun CurrentWeatherApiModel.toData(forecast: ForecastApiModel) = ForecastDataModel(
        cityName = forecast.city?.name.orEmpty(),
        cityId = forecast.city?.id ?: 0,
        cityForecast = forecast.forecast?.toData() ?: emptyList(),
        coordinates = CityCoordinatesDataModel(
            latitude = coord?.lat.orZero(),
            longitude = coord?.lon.orZero()
        ),
        seaLevel = forecast.forecast?.firstOrNull()?.main?.seaLevel ?: 0,
        weather = weather?.firstOrNull()?.main.orEmpty(),
        minimumTemperature = main?.tempMin.orZero(),
        maximumTemperature = main?.tempMax.orZero(),
        currentTemperature = main?.temp.orZero(),
    )

    fun List<ForecastApiModel.DayForecastApiModel>.toData() = map {
        CityForecastDataModel(
            date = it.dtTxt.orEmpty(),
            temperature = it.main?.temp.orZero(),
            minimumTemperature = it.main?.tempMax.orZero(),
            maximumTemperature = it.main?.tempMin.orZero(),
            weather = it.weather?.firstOrNull()?.main.orEmpty()
        )
    }
}