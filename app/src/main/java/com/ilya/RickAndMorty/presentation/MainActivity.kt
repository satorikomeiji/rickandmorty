package com.ilya.RickAndMorty.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.ilya.RickAndMorty.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recycler= findViewById<RecyclerView>(R.id.recycler)
        recycler.adapter =
    }
}
