package com.ilya.rickandmorty.repository

import android.content.Context
import androidx.paging.DataSource
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers

class CharacterRepository(private val context: Context) {

    private val rickAndMortyApiService = RickAndMortyApiService.create()

    private val characterDao: CharacterDao

    init {
        val roomDb = androidx.room.Room.databaseBuilder(
            context,
            AppDatabase::class.java, "rickAndMortyDB"
        )
            .fallbackToDestructiveMigration()
            .build()
        characterDao = roomDb.characterDao()
    }

    fun loadAllCharacters() : Completable {
        return rickAndMortyApiService.getAll()
            .subscribeOn(Schedulers.io())
            .doOnNext { characterDao.insert(it.results)}
            .ignoreElements()
    }

    fun getDataSourceFactory() : DataSource.Factory<Int, CharacterEntity> {
        return characterDao.observeAllPaged()
    }


}