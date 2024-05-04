package org.charr0max.herculeskmp.data.firebase

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import org.charr0max.herculeskmp.HerkulesDatabase

actual class DriverFactory {
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(HerkulesDatabase.Schema,"HerkulesDatabase.db")
    }
}