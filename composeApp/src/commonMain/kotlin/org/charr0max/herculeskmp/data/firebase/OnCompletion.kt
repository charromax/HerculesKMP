package org.charr0max.herculeskmp.data.firebase

interface onCompletion<T> {
    fun onSuccess(T : T)
    fun onError(e : Exception)
}