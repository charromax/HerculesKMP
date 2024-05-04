package org.charr0max.herculeskmp.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class DHTSensorData(
    override val model: SupportedModels = SupportedModels.Dht11Sensor,
    override val state: Boolean = false,
    val h: Float? = null,
    val t: Float? = null,
): Device() {
    override fun toString(): String {
        return "Model: $model - Hum: $h - Temp: $t Power: $state"
    }
}
