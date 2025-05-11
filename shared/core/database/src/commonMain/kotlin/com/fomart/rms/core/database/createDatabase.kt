package com.fomart.rms.core.database

fun createDatabase(databaseDriverFactory: DatabaseDriverFactory): FavoritesDatabase {
    val driver = databaseDriverFactory.createDriver()
    return FavoritesDatabase(driver)
}