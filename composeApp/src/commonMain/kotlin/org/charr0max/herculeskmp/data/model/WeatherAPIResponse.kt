package org.charr0max.herculeskmp.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherAPIResponse(
    @SerialName("coord") val coord: Coord? = Coord(),
    @SerialName("weather") val weather: ArrayList<Weather> = arrayListOf(),
    @SerialName("base") val base: String? = null,
    @SerialName("main") val main: Main = Main(),
    @SerialName("visibility") val visibility: Int? = null,
    @SerialName("wind") val wind: Wind? = Wind(),
    @SerialName("rain") val rain: Rain? = Rain(),
    @SerialName("clouds") val clouds: Clouds? = Clouds(),
    @SerialName("dt") val dt: Int? = null,
    @SerialName("sys") val sys: Sys? = Sys(),
    @SerialName("timezone") val timezone: Int? = null,
    @SerialName("id") val id: Int? = null,
    @SerialName("name") val name: String? = null,
    @SerialName("cod") val cod: Int? = null,
    @SerialName("dt_txt") val formattedTimestamp: String? = null
) {
    val iconUrl = "https://openweathermap.org/img/wn/${weather.firstOrNull()?.icon}@2x.png"
}

@Serializable
data class Coord(
    @SerialName("lon") val lon: Double? = null,
    @SerialName("lat") val lat: Double? = null
)

@Serializable
data class Weather(
    @SerialName("id") val id: Int? = null,
    @SerialName("main") val main: String? = null,
    @SerialName("description") val description: String? = null,
    @SerialName("icon") val icon: String? = null
)

@Serializable
data class Main(
    @SerialName("temp") val temp: Double? = null,
    @SerialName("feels_like") val feelsLike: Double? = null,
    @SerialName("temp_min") val tempMin: Double? = null,
    @SerialName("temp_max") val tempMax: Double? = null,
    @SerialName("pressure") val pressure: Int? = null,
    @SerialName("humidity") val humidity: Int? = null,
    @SerialName("sea_level") val seaLevel: Int? = null,
    @SerialName("grnd_level") val grndLevel: Int? = null
)

@Serializable
data class Wind(
    @SerialName("speed") val speed: Double? = null,
    @SerialName("deg") val deg: Int? = null,
    @SerialName("gust") val gust: Double? = null
)

@Serializable
data class Rain(
    @SerialName("1h") val h: Double? = null
)

@Serializable
data class Clouds(
    @SerialName("all") val all: Int? = null
)

@Serializable
data class Sys(
    @SerialName("type") val type: Int? = null,
    @SerialName("id") val id: Int? = null,
    @SerialName("country") val country: String? = null,
    @SerialName("sunrise") val sunrise: Int? = null,
    @SerialName("sunset") val sunset: Int? = null
)