package com.ilya.rickandmorty

import android.app.Application

class App : Application() {


    override fun onCreate() {
        super.onCreate()
        instance = this
    }


    companion object {

        private lateinit var instance: App

        fun instance(): App {
            return instance
        }
    }

}