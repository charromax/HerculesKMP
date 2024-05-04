package org.charr0max.herculeskmp.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherForecastAPIResponse(
    @SerialName("list") var daily: List<WeatherAPIResponse>
) {
    val filteredEarlyMorning = daily.filter { it.formattedTimestamp.orEmpty().contains("03:00:00") }
    val currentEarlyMorning = filteredEarlyMorning.firstOrNull()
    val tomorrowEarlyMorning = filteredEarlyMorning.getOrNull(1)

    val filteredNoon = daily.filter { it.formattedTimestamp.orEmpty().contains("12:00:00") }
    val currentNoon = filteredNoon.firstOrNull()
    val tomorrowNoon = filteredNoon.getOrNull(1)
}
