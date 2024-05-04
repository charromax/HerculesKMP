package org.charr0max.herculeskmp.domain.repository

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.database.DatabaseReference
import dev.gitlive.firebase.database.database
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.charr0max.herculeskmp.domain.model.DHTSensorData
import org.charr0max.herculeskmp.domain.model.MC38SensorData
import org.charr0max.herculeskmp.domain.model.SupportedModels

private const val SensorsReference = "sensors"

class FirebaseDatabaseRepositoryImpl(
    private val sensorRef: DatabaseReference = Firebase.database.reference(SensorsReference),
) : FirebaseDatabaseRepository {

    override fun getDhtSensors(): Flow<List<DHTSensorData>> =
        sensorRef.child(SupportedModels.Dht11Sensor.name).valueEvents.map {
            it.children.map {
                it.value(
                    DHTSensorData.serializer()
                )
            }
        }

    override fun getMc38Sensors(): Flow<List<MC38SensorData>> =
        sensorRef.child(SupportedModels.Mc38DoorSensor.name).valueEvents.map {
            it.children.map {
                it.value(MC38SensorData.serializer())
            }
        }

    override suspend fun setAmbientSensorData() {
        val sensorData = DHTSensorData(h= 45f, t = 23.2f)
        val triggerData = MC38SensorData()

        sensorRef.child(sensorData.model.name).child(sensorData.id)
            .setValue(DHTSensorData.serializer(), sensorData, true)

        sensorRef.child(triggerData.model.name).child(triggerData.id)
            .setValue(MC38SensorData.serializer(), triggerData, true)
    }
}
