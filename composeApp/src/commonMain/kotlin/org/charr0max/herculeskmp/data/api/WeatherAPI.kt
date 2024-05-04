package org.charr0max.herculeskmp.data.api

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import org.charr0max.herculeskmp.data.model.WeatherForecastAPIResponse

private const val API_KEY = "bf71647e6f9abb1eac46b72ae665015e"



class WeatherAPI(
    private val httpClient: HttpClient = HttpClient()
) {
    private val jsonBuilder = Json { ignoreUnknownKeys = true }
    private val forecastWeatherApiUrl =
        "https://api.openweathermap.org/data/2.5/forecast?q=Rosario&unit=metric&appid=$API_KEY"

    @OptIn(DelicateCoroutinesApi::class)
    fun getWeatherForecastAPIData(
        onSuccess: (WeatherForecastAPIResponse) -> Unit, onError: (String) -> Unit
    ) {
        GlobalScope.launch(ApplicationDispatcher) {
            try {
                val url = forecastWeatherApiUrl
                val json = httpClient.get(url) {}
                jsonBuilder.decodeFromString(WeatherForecastAPIResponse.serializer(), json.bodyAsText())
                    .also(onSuccess)
                } catch (ex: Exception) {
                onError("${ex.message}")
            }
        }
    }
}