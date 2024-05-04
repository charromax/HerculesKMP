package org.charr0max.herculeskmp.domain.model

import com.benasher44.uuid.uuid4
import kotlinx.serialization.Serializable

@Serializable
abstract class Device(
    val id: String = uuid4().toString()
) {
    abstract val model: SupportedModels
    abstract val state: Boolean
}