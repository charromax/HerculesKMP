package org.charr0max.herculeskmp.domain.model

data class WeatherSensorData(
    val currentWeatherIcon: String,
    val currentTemp: Double,
    val tempMin: Double,
    val tempMax: Double,
)
