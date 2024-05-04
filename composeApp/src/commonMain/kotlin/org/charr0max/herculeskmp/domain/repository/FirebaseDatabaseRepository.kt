package org.charr0max.herculeskmp.domain.repository

import kotlinx.coroutines.flow.Flow
import org.charr0max.herculeskmp.domain.model.DHTSensorData
import org.charr0max.herculeskmp.domain.model.MC38SensorData

interface FirebaseDatabaseRepository {
    fun getDhtSensors(): Flow<List<DHTSensorData>>
    fun getMc38Sensors(): Flow<List<MC38SensorData>>
    suspend fun setAmbientSensorData()
}
