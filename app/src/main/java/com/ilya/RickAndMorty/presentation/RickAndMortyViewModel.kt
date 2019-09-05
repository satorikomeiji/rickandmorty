package com.ilya.RickAndMorty.presentation

import androidx.lifecycle.ViewModel
import com.ilya.RickAndMorty.App
import com.ilya.RickAndMorty.repository.CharacterRepository


class RickAndMortyViewModel : ViewModel() {

    private val characterRepository : CharacterRepository

    init {
        characterRepository = CharacterRepository(App.instance())
        characterRepository.loadAllCharacters()
        characterRepository.
    }
}