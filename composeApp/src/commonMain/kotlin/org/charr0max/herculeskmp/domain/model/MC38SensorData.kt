package org.charr0max.herculeskmp.domain.model

import kotlinx.serialization.Serializable
@Serializable
data class MC38SensorData(
    override val model: SupportedModels = SupportedModels.Mc38DoorSensor,
    override val state: Boolean = false,
    val triggered: Boolean = false,
): Device()
