package org.charr0max.herculeskmp.data.firebase

import app.cash.sqldelight.db.SqlDriver
import org.charr0max.herculeskmp.HerkulesDatabase

expect class DriverFactory {
    fun createDriver(): SqlDriver
}

fun createDatabase(driverFactory: DriverFactory): HerkulesDatabase {
    val driver = driverFactory.createDriver()
    val database = HerkulesDatabase(driver)

    // Do more work with the database (see below).
    return database
}
