package org.charr0max.herculeskmp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory
import org.charr0max.herculeskmp.cache.HerkulesDatabase
import org.charr0max.herculeskmp.data.firebase.Firebase
import org.charr0max.herculeskmp.presentation.home.HomeViewModel
import org.charr0max.herculeskmp.presentation.home.components.TriggerSensorComponent
import org.charr0max.herculeskmp.presentation.home.components.WeatherInformationComponent
import org.charr0max.herculeskmp.presentation.theme.HerkulesTheme

@Composable
fun App(herkulesDatabase: HerkulesDatabase) {
    val firebase = Firebase()
    firebase.initialize(
        apiKey = "AIzaSyDwQ4T6xztGGqk17dx5V7zE5h4mZzuAvmE",
        databaseUrl = "https://herculeskmp-default-rtdb.firebaseio.com"
    )
    HerkulesTheme {
        val viewModel = getViewModel(Unit, viewModelFactory { HomeViewModel() })
        val dhtSensors = viewModel.ambientSensors.collectAsState(emptyList())
        val triggerSensors = viewModel.triggerSensors.collectAsState(emptyList())
        val forecast = viewModel.forecastData.collectAsState(null)

        Column(
            modifier = Modifier.fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background).padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            dhtSensors.value.lastOrNull()?.let { dhtData ->
                forecast.value?.let { forecastData ->
                    WeatherInformationComponent(
                        modifier = Modifier.height(250.dp),
                        dhtSensorData = dhtData,
                        weatherData = forecastData
                    )
                }
            }
            triggerSensors.value.lastOrNull()?.let {
                TriggerSensorComponent(data = it)
            }
        }
    }

}

