package com.ilya.RickAndMorty.repository

import android.content.Context
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


}