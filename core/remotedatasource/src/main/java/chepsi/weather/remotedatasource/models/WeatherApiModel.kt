package chepsi.weather.remotedatasource.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherApiModel(
    @SerialName("base")
    val base: String? = null,
    @SerialName("clouds")
    val clouds: Clouds? = null,
    @SerialName("cod")
    val cod: Int? = null,
    @SerialName("coord")
    val coord: Coord? = null,
    @SerialName("dt")
    val dt: Int? = null,
    @SerialName("id")
    val id: Int? = null,
    @SerialName("main")
    val main: Main? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("rain")
    val rain: Rain? = null,
    @SerialName("sys")
    val sys: Sys? = null,
    @SerialName("timezone")
    val timezone: Int? = null,
    @SerialName("visibility")
    val visibility: Int? = null,
    @SerialName("weather")
    val weather: List<Weather?>? = null,
    @SerialName("wind")
    val wind: Wind? = null
) {
    @Serializable
    data class Clouds(
        @SerialName("all")
        val all: Int? = null
    )

    @Serializable
    data class Coord(
        @SerialName("lat")
        val lat: Double? = null,
        @SerialName("lon")
        val lon: Double? = null
    )

    @Serializable
    data class Main(
        @SerialName("feels_like")
        val feelsLike: Double? = null,
        @SerialName("grnd_level")
        val grndLevel: Int? = null,
        @SerialName("humidity")
        val humidity: Int? = null,
        @SerialName("pressure")
        val pressure: Int? = null,
        @SerialName("sea_level")
        val seaLevel: Int? = null,
        @SerialName("temp")
        val temp: Double? = null,
        @SerialName("temp_max")
        val tempMax: Double? = null,
        @SerialName("temp_min")
        val tempMin: Double? = null
    )

    @Serializable
    data class Rain(
        @SerialName("1h")
        val h: Double? = null
    )

    @Serializable
    data class Sys(
        @SerialName("country")
        val country: String? = null,
        @SerialName("id")
        val id: Int? = null,
        @SerialName("sunrise")
        val sunrise: Int? = null,
        @SerialName("sunset")
        val sunset: Int? = null,
        @SerialName("type")
        val type: Int? = null
    )

    @Serializable
    data class Weather(
        @SerialName("description")
        val description: String? = null,
        @SerialName("icon")
        val icon: String? = null,
        @SerialName("id")
        val id: Int? = null,
        @SerialName("main")
        val main: String? = null
    )

    @Serializable
    data class Wind(
        @SerialName("deg")
        val deg: Int? = null,
        @SerialName("gust")
        val gust: Double? = null,
        @SerialName("speed")
        val speed: Double? = null
    )
}
