package chepsi.weather.presentation.screens.main.mapper

import chepsi.weather.presentation.screens.main.mapper.DateMapper.toDayOfWeek
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class DateMapperTest {

    @Test
    fun `Given correct date When toDomain() Then returns correct date`() {
        //
        val givenDateString = "2023-08-24 18:00:00"
        val expectedValue = "Thursday"
        val actualValue = givenDateString.toDayOfWeek()

        assertEquals(expectedValue, actualValue)
    }

    @Test
    fun `Given wrong date format When toDomain() Then returns input date`() {
        //
        val givenDateString = "2023-08-S2 18:00:00"
        val expectedValue = "2023-08-S2 18:00:00"
        val actualValue = givenDateString.toDayOfWeek()

        assertEquals(expectedValue, actualValue)
    }
}
