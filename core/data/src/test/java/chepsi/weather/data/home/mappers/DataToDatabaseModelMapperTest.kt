package chepsi.weather.data.home.mappers

import chepsi.weather.data.home.mappers.DataToDatabaseModelMapper.toCityEntity
import chepsi.weather.data.home.mappers.DataToDatabaseModelMapper.toForecastEntity
import chepsi.weather.data.home.mappers.DataToDatabaseModelMapper.toWeatherEntity
import chepsi.weather.data.home.model.CityCoordinatesDataModel
import chepsi.weather.data.home.model.CityForecastDataModel
import chepsi.weather.data.home.model.ForecastDataModel
import chepsi.weather.localdatasource.city.model.CityEntity
import chepsi.weather.localdatasource.weather.model.ForecastEntity
import chepsi.weather.localdatasource.weather.model.WeatherLocalSourceEntity
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

private val givenDataModel = ForecastDataModel(
    cityId = 1,
    cityName = "Gotham",
    cityForecast = listOf(
        CityForecastDataModel(
            date = "2023-08-24 12:00:00",
            temperature = 20.0,
            minimumTemperature = 10.0,
            maximumTemperature = 22.0,
            weather = "Clear"
        )
    ),
    seaLevel = 1,
    weather = "Clear",
    coordinates = CityCoordinatesDataModel(10.0, 10.0),
    minimumTemperature = 12.0,
    maximumTemperature = 20.0,
    currentTemperature = 19.0
)

private val expectedWeatherEntity = WeatherLocalSourceEntity(
    cityId = 1,
    cityName = "Gotham",
    seaLevel = 1,
    weather = "Clear",
    minimumTemperature = 12.0,
    maximumTemperature = 20.0,
    temperature = 19.0,
    createdAt = 10L
)
private val expectedCity = CityEntity(
    cityId = 1,
    cityName = "Gotham",
    isCurrent = true,
    latitude = 10.0,
    longitude = 10.0
)
private val expectedForecast = listOf(
    ForecastEntity(
        id = 0,
        date = "2023-08-24 12:00:00",
        minimumTemperature = 10.0,
        maximumTemperature = 22.0,
        temperature = 20.0,
        weather = "Clear",
        cityId = 1
    )
)

class DataToDatabaseModelMapperTest {

    @Test
    fun `Given data model When toWeatherEntity Then maps correctly`() {
        // Then
        val actual = givenDataModel.toWeatherEntity(10L)

        // When
        assertEquals(expectedWeatherEntity, actual)
    }

    @Test
    fun `Given data model When toCityEntity Then maps correctly`() {
        // Then
        val actual = givenDataModel.toCityEntity()

        // When
        assertEquals(expectedCity, actual)
    }

    @Test
    fun `Given data model When toForecastEntity Then maps correctly`() {
        // Then
        val actual = givenDataModel.toForecastEntity()

        // When
        assertEquals(expectedForecast, actual)
    }
}
