package org.charr0max.herculeskmp.data.firebase

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import org.charr0max.herculeskmp.HerkulesDatabase

actual class DriverFactory(private val appContext: Context) {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(HerkulesDatabase.Schema, appContext, "HerkulesDatabase.db")
    }
}