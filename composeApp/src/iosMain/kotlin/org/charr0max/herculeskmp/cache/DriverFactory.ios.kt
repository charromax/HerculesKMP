package org.charr0max.herculeskmp.cache

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver

class IOSDriverFactory : DriverFactory {
    override fun createDriver(): SqlDriver {
        return NativeSqliteDriver(HerkulesDatabase.Schema, "HerkulesDatabase.db")
    }
}
