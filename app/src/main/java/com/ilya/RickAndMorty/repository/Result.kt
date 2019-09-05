package com.ilya.RickAndMorty.repository

data class Result(var info: Info, var results: List<CharacterEntity>)

data class Info(var count: Int, var pages: Int, var next: String?, val prev: String?)