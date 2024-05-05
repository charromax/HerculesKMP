package org.charr0max.herculeskmp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import org.charr0max.herculeskmp.cache.AndroidDriverFactory
import org.charr0max.herculeskmp.cache.Database

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val driverFactory = AndroidDriverFactory(this)
        val herkulesDatabase = Database(driverFactory).database
        super.onCreate(savedInstanceState)
        setContent {
            App(herkulesDatabase)
        }
    }
}


