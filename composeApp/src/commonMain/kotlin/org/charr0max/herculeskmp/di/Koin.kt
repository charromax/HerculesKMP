package org.charr0max.herculeskmp.di

import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

class Koin {
    fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
        startKoin {
            appDeclaration()
        }
}