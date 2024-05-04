package org.charr0max.herculeskmp.util

import kotlin.math.roundToInt

fun Float.normalize(min: Float = 0f, max: Float = 100f): Float {
    return (this - min) / (max - min)
}

fun Double.roundToDecimals(decimals: Int = 1): String {
    var dotAt = 1
    repeat(decimals) { dotAt *= 10 }
    val roundedValue = ((this/10) * dotAt).roundToInt()
    return ((roundedValue / dotAt) + (roundedValue % dotAt).toDouble() / dotAt).toString()
}
fun Float.roundToDecimals(decimals: Int = 1): String {
    var dotAt = 1
    repeat(decimals) { dotAt *= 10 }
    val roundedValue = ((this/10) * dotAt).roundToInt()
    return ((roundedValue / dotAt) + (roundedValue % dotAt).toFloat() / dotAt).toString()
}