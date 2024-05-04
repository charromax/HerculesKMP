package org.charr0max.herculeskmp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import org.charr0max.herculeskmp.data.firebase.DriverFactory
import org.charr0max.herculeskmp.data.firebase.createDatabase

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val driverFactory = DriverFactory(this)
        val herkulesDatabase = createDatabase(driverFactory)
        super.onCreate(savedInstanceState)
        setContent {
            App(herkulesDatabase)
        }
    }
}


