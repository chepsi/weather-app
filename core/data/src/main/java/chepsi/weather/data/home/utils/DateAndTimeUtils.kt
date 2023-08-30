package chepsi.weather.data.home.utils

import java.text.SimpleDateFormat
import java.util.Locale

class DateAndTimeUtils {

    fun todayInMillis() = System.currentTimeMillis()

    fun convertToDate(date: Long): String =
        SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(date)

    fun convertToLong(date: String): Long = SimpleDateFormat(
        "yyyy-MM-dd HH:mm:ss",
        Locale.ENGLISH
    ).parse(date)?.time ?: 0L

    fun todayInDate(): String = convertToDate(todayInMillis())
}
