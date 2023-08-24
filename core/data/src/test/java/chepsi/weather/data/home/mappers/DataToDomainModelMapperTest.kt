package chepsi.weather.data.home.mappers

import chepsi.weather.data.home.mappers.DataToDomainModelMapper.toDomain
import chepsi.weather.data.home.model.CityCoordinatesDataModel
import chepsi.weather.data.home.model.CityForecastDataModel
import chepsi.weather.data.home.model.ForecastDataModel
import chepsi.weather.domain.home.model.ForecastDomainModel
import chepsi.weather.domain.home.model.HomeRepositoryDomainModel
import chepsi.weather.domain.home.model.WeatherDomainModel
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

private val expectedValue = HomeRepositoryDomainModel(
    currentTemperature = 19,
    minimumTemperature = 12,
    maximumTemperature = 20,
    weather = WeatherDomainModel.Sunny,
    seaLevel = 1000,
    daysAheadForecast = listOf(
        ForecastDomainModel(
            day = "2023-08-24 12:00:00",
            weather = WeatherDomainModel.Sunny,
            temperature = 20
        )
    )
)

class DataToDomainModelMapperTest {

    @Test
    fun `Given data model When toDomain Then maps correctly`() {
        // Given
        val actual = givenDataModel.toDomain()

        // When
        assertEquals(expectedValue, actual)
    }
}
