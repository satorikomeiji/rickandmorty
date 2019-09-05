package com.ilya.RickAndMorty.presentation

import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import androidx.paging.RxPagedListBuilder
import com.ilya.RickAndMorty.App
import com.ilya.RickAndMorty.repository.CharacterEntity
import com.ilya.RickAndMorty.repository.CharacterRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import java.util.concurrent.Executors


class RickAndMortyViewModel : ViewModel() {

    private val characterRepository : CharacterRepository

    val result = BehaviorSubject.create<PagedList<CharacterEntity>>()

    private var compositeDisposable : CompositeDisposable? = null

    init {
        compositeDisposable = CompositeDisposable()
        characterRepository = CharacterRepository(App.instance())
        compositeDisposable?.add(characterRepository
            .loadAllCharacters()
            .subscribe(
                {},
                { it.printStackTrace() })
        )

        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(30)
            .build()

        val executor = Executors.newSingleThreadExecutor()
        val scheduler = Schedulers.from(executor)
        val dataSourceFactory = characterRepository.getDataSourceFactory()
        compositeDisposable?.add(RxPagedListBuilder(dataSourceFactory, config)
            .setFetchScheduler(scheduler)
            .setNotifyScheduler(scheduler)
            .buildObservable()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe( { result.onNext(it)}))

    }

    override fun onCleared() {
        compositeDisposable?.dispose()
        super.onCleared()
    }
}