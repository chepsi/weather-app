package chepsi.weather.data.home.mappers

import chepsi.weather.data.home.mappers.DataToDomainModelMapper.toWeatherConditionDomainMapper
import chepsi.weather.domain.home.model.WeatherDomainModel.Cloudy
import chepsi.weather.domain.home.model.WeatherDomainModel.Default
import chepsi.weather.domain.home.model.WeatherDomainModel.Rainy
import chepsi.weather.domain.home.model.WeatherDomainModel.Sunny
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class DataStringToWeatherConditionsDomainModelMapper {

    @Test
    fun `Given cloudy weather When toDomain Returns weather domain model`() {
        // When
        val actual = "Clouds".toWeatherConditionDomainMapper()

        // Then
        val expected = Cloudy
        assertEquals(expected, actual)
    }

    @Test
    fun `Given rainy weather When toDomain Returns weather domain model`() {
        // When
        val actual = "Rain".toWeatherConditionDomainMapper()

        // Then
        val expected = Rainy
        assertEquals(expected, actual)
    }

    @Test
    fun `Given clear weather When toDomain Returns weather domain model`() {
        // When
        val actual = "Clear".toWeatherConditionDomainMapper()

        // Then
        val expected = Sunny
        assertEquals(expected, actual)
    }

    @Test
    fun `Given undefined weather When toDomain Returns weather domain model`() {
        // When
        val actual = "Gotham".toWeatherConditionDomainMapper()

        // Then
        val expected = Default
        assertEquals(expected, actual)
    }
}
