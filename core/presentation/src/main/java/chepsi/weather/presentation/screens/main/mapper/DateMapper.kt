package chepsi.weather.presentation.screens.main.mapper

import java.text.SimpleDateFormat
import java.util.Locale

object DateMapper {
    fun String.toDayOfWeek(): String = try {
        val date = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH).parse(this)
        date?.let { SimpleDateFormat("EEEE", Locale.ENGLISH).format(it) } ?: ""
    } catch (e: Exception) {
        this
    }
}
