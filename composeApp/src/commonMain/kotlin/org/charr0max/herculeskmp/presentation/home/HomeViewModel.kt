package org.charr0max.herculeskmp.presentation.home

import dev.icerock.moko.mvvm.viewmodel.ViewModel
import org.charr0max.herculeskmp.domain.repository.FirebaseDatabaseRepository
import org.charr0max.herculeskmp.domain.repository.FirebaseDatabaseRepositoryImpl
import org.charr0max.herculeskmp.domain.model.DHTSensorData
import org.charr0max.herculeskmp.domain.model.MC38SensorData
import org.charr0max.herculeskmp.domain.repository.WeatherRepository
import org.charr0max.herculeskmp.domain.repository.WeatherRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

data class HomeScreenData(
    val loading: Boolean = false,
    val ambientSensors: List<DHTSensorData> = emptyList(),
    val triggerSensors: List<MC38SensorData> = emptyList(),
)

class HomeViewModel(
    private val firebaseDatabaseRepository: FirebaseDatabaseRepository = FirebaseDatabaseRepositoryImpl(),
    weatherRepository: WeatherRepository = WeatherRepositoryImpl()
) : ViewModel() {

    private val _state = MutableStateFlow(HomeScreenData())
    val state = _state.asStateFlow()

    val ambientSensors = firebaseDatabaseRepository.getDhtSensors()
    val triggerSensors = firebaseDatabaseRepository.getMc38Sensors()
    val forecastData = weatherRepository.getForecastWeatherData()

    suspend fun setSensorData() {
//        firebaseRepository.setAmbientSensorData()
    }
}