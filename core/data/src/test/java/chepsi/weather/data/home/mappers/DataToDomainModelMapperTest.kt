package chepsi.weather.data.home.mappers

import chepsi.weather.data.home.model.CityCoordinatesDataModel
import chepsi.weather.data.home.model.CityForecastDataModel
import chepsi.weather.data.home.model.ForecastDataModel
import chepsi.weather.data.home.utils.DateAndTimeUtils
import chepsi.weather.domain.home.model.ForecastDomainModel
import chepsi.weather.domain.home.model.HomeRepositoryDomainModel
import chepsi.weather.domain.home.model.WeatherDomainModel
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

private const val givenForecastDate = "2023-08-24 12:00:00"
private val givenDataModel = ForecastDataModel(
    cityId = 1,
    cityName = "Gotham",
    cityForecast = listOf(
        CityForecastDataModel(
            date = givenForecastDate,
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

private val expectedValue0 = HomeRepositoryDomainModel(
    currentTemperature = 19,
    minimumTemperature = 12,
    maximumTemperature = 20,
    weather = WeatherDomainModel.Sunny,
    seaLevel = 1,
    daysAheadForecast = listOf(
        ForecastDomainModel(
            day = "2023-08-24 12:00:00",
            weather = WeatherDomainModel.Sunny,
            temperature = 20
        )
    ),
    cityName = "Gotham"
)

private val expectedValue1 = HomeRepositoryDomainModel(
    currentTemperature = 19,
    minimumTemperature = 12,
    maximumTemperature = 20,
    weather = WeatherDomainModel.Sunny,
    seaLevel = 1,
    daysAheadForecast = emptyList(),
    cityName = "Gotham"
)

class DataToDomainModelMapperTest {

    private lateinit var classUnderTest: DataToDomainModelMapper
    private val dateAndTimeUtils = mockk<DateAndTimeUtils>()

    @BeforeEach
    fun setup() {
        classUnderTest = DataToDomainModelMapper(dateAndTimeUtils)
    }

    @AfterEach
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `Given data model When toDomain Then maps correctly`() {
        // Given
        every { dateAndTimeUtils.todayInMillis() } returns 10L
        every { dateAndTimeUtils.convertToLong(givenForecastDate) } returns 20L
        val actual = classUnderTest.toDomain(givenDataModel)

        // When
        assertEquals(expectedValue0, actual)
    }

    @Test
    fun `Given historical data model When toDomain Then maps correctly`() {
        // Given
        every { dateAndTimeUtils.todayInMillis() } returns 10L
        every { dateAndTimeUtils.convertToLong(givenForecastDate) } returns 9L
        val actual = classUnderTest.toDomain(givenDataModel)

        // When
        assertEquals(expectedValue1, actual)
    }
}
