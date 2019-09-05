package com.ilya.rickandmorty.repository

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(CharacterEntity::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao
}