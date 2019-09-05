package com.ilya.rickandmorty.repository

import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


interface RickAndMortyApiService {

    @GET("character")
    fun getAll(): Observable<Result>

    /**
     * Companion object to create the GithubApiService
     */
    companion object Factory {
        fun create(): RickAndMortyApiService {
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://rickandmortyapi.com/api/")
                .build()

            return retrofit.create(RickAndMortyApiService::class.java);
        }
    }
}