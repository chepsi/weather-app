package chepsi.weather.remote_data_source.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ForecastApiModel(
    @SerialName("city")
    val city: City? = null,
    @SerialName("cnt")
    val cnt: Int? = null,
    @SerialName("cod")
    val cod: String? = null,
    @SerialName("list")
    val list: List<DayForecastApiModel>? = null,
    @SerialName("message")
    val message: Int? = null
) {
    @Serializable
    data class City(
        @SerialName("coord")
        val coord: Coord? = null,
        @SerialName("country")
        val country: String? = null,
        @SerialName("id")
        val id: Int? = null,
        @SerialName("name")
        val name: String? = null,
        @SerialName("population")
        val population: Int? = null,
        @SerialName("sunrise")
        val sunrise: Int? = null,
        @SerialName("sunset")
        val sunset: Int? = null,
        @SerialName("timezone")
        val timezone: Int? = null
    ) {
        @Serializable
        data class Coord(
            @SerialName("lat")
            val lat: Double? = null,
            @SerialName("lon")
            val lon: Double? = null
        )
    }

    @Serializable
    data class DayForecastApiModel(
        @SerialName("clouds")
        val clouds: Clouds? = null,
        @SerialName("dt")
        val dt: Int? = null,
        @SerialName("dt_txt")
        val dtTxt: String? = null,
        @SerialName("main")
        val main: Main? = null,
        @SerialName("pop")
        val pop: Double? = null,
        @SerialName("rain")
        val rain: Rain? = null,
        @SerialName("sys")
        val sys: Sys? = null,
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
            @SerialName("temp_kf")
            val tempKf: Double? = null,
            @SerialName("temp_max")
            val tempMax: Double? = null,
            @SerialName("temp_min")
            val tempMin: Double? = null
        )

        @Serializable
        data class Rain(
            @SerialName("3h")
            val h: Double? = null
        )

        @Serializable
        data class Sys(
            @SerialName("pod")
            val pod: String? = null
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
}