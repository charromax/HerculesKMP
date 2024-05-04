package org.charr0max.herculeskmp.domain.repository

import org.charr0max.herculeskmp.data.model.WeatherForecastAPIResponse
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    fun getForecastWeatherData(): Flow<WeatherForecastAPIResponse?>
}