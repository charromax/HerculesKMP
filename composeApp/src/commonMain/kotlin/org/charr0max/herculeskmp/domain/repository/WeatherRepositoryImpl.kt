package org.charr0max.herculeskmp.domain.repository

import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import org.charr0max.herculeskmp.data.api.WeatherAPI
import org.charr0max.herculeskmp.data.model.WeatherForecastAPIResponse

class WeatherRepositoryImpl(
    private val api: WeatherAPI = WeatherAPI()
) : WeatherRepository {

    override fun getForecastWeatherData(): Flow<WeatherForecastAPIResponse?> = callbackFlow {
        api.getWeatherForecastAPIData(
            onSuccess = {
                trySend(it)
            },
            onError = {
                println("Networking Error: $it")
                trySend(null)
            }
        )
        awaitClose()
    }
}