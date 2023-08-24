package chepsi.weather.data.home.mappers

import chepsi.weather.data.home.mappers.ApiToDataModelMapper.toData
import chepsi.weather.data.home.model.CityCoordinatesDataModel
import chepsi.weather.data.home.model.CityForecastDataModel
import chepsi.weather.data.home.model.ForecastDataModel
import chepsi.weather.remotedatasource.models.ForecastApiModel
import chepsi.weather.remotedatasource.models.WeatherApiModel
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

private val givenForecastApiModel0 = ForecastApiModel(
    city = ForecastApiModel.City(
        name = "Gotham",
        id = 1
    ),
    forecast = listOf(
        ForecastApiModel.DayForecastApiModel(
            dtTxt = "2023-08-24 18:00:00",
            main = ForecastApiModel.DayForecastApiModel.Main(
                temp = 20.0,
                tempMax = 21.0,
                tempMin = 19.0,
                seaLevel = 100
            ),
            weather = listOf(
                ForecastApiModel.DayForecastApiModel.Weather(
                    main = "Clear"
                )
            )
        )
    )
)
private val givenApiModel0 = WeatherApiModel(
    coord = WeatherApiModel.Coord(10.0, 10.0),
    weather = listOf(
        WeatherApiModel.Weather(main = "Clear")
    ),
    main = WeatherApiModel.Main(
        tempMin = 19.0,
        tempMax = 21.0,
        temp = 20.0
    )

)
private val givenApiModel1 = WeatherApiModel()
private val givenForecastApiModel1 = ForecastApiModel()

private val expectedDataModel0 = ForecastDataModel(
    cityId = 1,
    cityName = "Gotham",
    cityForecast = listOf(
        CityForecastDataModel(
            date = "2023-08-24 18:00:00",
            temperature = 20.0,
            minimumTemperature = 19.0,
            maximumTemperature = 21.0,
            weather = "Clear"
        )
    ),
    seaLevel = 100,
    weather = "Clear",
    coordinates = CityCoordinatesDataModel(10.0, 10.0),
    minimumTemperature = 19.0,
    maximumTemperature = 21.0,
    currentTemperature = 20.0
)

private val expectedDataModel1 = ForecastDataModel(
    cityId = 0,
    cityName = "",
    cityForecast = emptyList(),
    seaLevel = 0,
    weather = "",
    coordinates = CityCoordinatesDataModel(0.0, 0.0),
    minimumTemperature = 0.0,
    maximumTemperature = 0.0,
    currentTemperature = 0.0
)

class ApiToDataModelMappersTest {

    @Test
    fun `Given Api response When toData() Then maps correctly`() {
        // When
        val actual = givenApiModel0.toData(givenForecastApiModel0)

        // Then
        assertEquals(expectedDataModel0, actual)
    }

    @Test
    fun `Given empty Api response When toData() Then maps correctly`() {
        // When
        val actual = givenApiModel1.toData(givenForecastApiModel1)

        // Then
        assertEquals(expectedDataModel1, actual)
    }
}
