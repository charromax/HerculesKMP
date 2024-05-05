package org.charr0max.herculeskmp.cache

import app.cash.sqldelight.db.SqlDriver

interface DriverFactory {
    fun createDriver(): SqlDriver
}

internal class Database(databaseDriverFactory: DriverFactory) {
    val database = HerkulesDatabase(databaseDriverFactory.createDriver())
    private val dbQuery = database.herkulesDatabaseQueries

    fun createNewUser(email: String, name: String) {
        dbQuery.insertUser(id = null, email = email, name = name)
    }
}
