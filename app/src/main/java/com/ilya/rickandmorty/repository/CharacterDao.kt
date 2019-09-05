package com.ilya.rickandmorty.repository

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
abstract  class CharacterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(characters: List<CharacterEntity>)

    @Query("SELECT * FROM character")
    abstract fun observeAllPaged(): DataSource.Factory<Int, CharacterEntity>
}