package com.ilya.rickandmorty.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ilya.rickandmorty.R
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable

class MainActivity : AppCompatActivity() {

    private var compositeDisposable : CompositeDisposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recycler = findViewById<RecyclerView>(R.id.recycler)
        val adapter = RickAndMortyAdapter()
        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(this)

        compositeDisposable = CompositeDisposable()
        val model = ViewModelProviders.of(this).get(RickAndMortyViewModel::class.java)
        compositeDisposable?.add(model
            .result
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { adapter.submitList(it) },
                { it.printStackTrace() }
            )
        )

    }

    override fun onDestroy() {
        compositeDisposable?.dispose()
        super.onDestroy()
    }
}
