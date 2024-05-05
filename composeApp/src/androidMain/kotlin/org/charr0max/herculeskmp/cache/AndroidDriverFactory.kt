package org.charr0max.herculeskmp.cache

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver

class AndroidDriverFactory(private val appContext: Context) : DriverFactory {
    override fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(HerkulesDatabase.Schema, appContext, "HerkulesDatabase.db")
    }
}