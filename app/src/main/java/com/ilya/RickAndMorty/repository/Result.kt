package com.ilya.RickAndMorty.repository

data class Result(val info: Info, val results: List<CharacterEntity>)

data class Info(val count: Int, val pages: Int, val next: String?, val prev: String?)