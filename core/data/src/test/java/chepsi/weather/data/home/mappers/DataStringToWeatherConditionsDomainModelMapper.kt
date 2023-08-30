package chepsi.weather.data.home.mappers

import chepsi.weather.data.home.utils.DateAndTimeUtils
import chepsi.weather.domain.home.model.WeatherDomainModel.Cloudy
import chepsi.weather.domain.home.model.WeatherDomainModel.Default
import chepsi.weather.domain.home.model.WeatherDomainModel.Rainy
import chepsi.weather.domain.home.model.WeatherDomainModel.Sunny
import io.mockk.clearAllMocks
import io.mockk.mockk
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class DataStringToWeatherConditionsDomainModelMapper {

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
    fun `Given cloudy weather When toDomain Returns weather domain model`() {
        // When
        val actual = classUnderTest.weatherConditionDomainMapper("Clouds")

        // Then
        val expected = Cloudy
        assertEquals(expected, actual)
    }

    @Test
    fun `Given rainy weather When toDomain Returns weather domain model`() {
        // When
        val actual = classUnderTest.weatherConditionDomainMapper("Rain")

        // Then
        val expected = Rainy
        assertEquals(expected, actual)
    }

    @Test
    fun `Given clear weather When toDomain Returns weather domain model`() {
        // When
        val actual = classUnderTest.weatherConditionDomainMapper("Clear")

        // Then
        val expected = Sunny
        assertEquals(expected, actual)
    }

    @Test
    fun `Given undefined weather When toDomain Returns weather domain model`() {
        // When
        val actual = classUnderTest.weatherConditionDomainMapper("Gotham")

        // Then
        val expected = Default
        assertEquals(expected, actual)
    }
}
