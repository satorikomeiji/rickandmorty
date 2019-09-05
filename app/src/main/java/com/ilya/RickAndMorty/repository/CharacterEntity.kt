package com.ilya.RickAndMorty.repository

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "character")

data class CharacterEntity(
    @PrimaryKey var id: Long,
    var name: String?,
    var status: String?,
    var species: String?,
    var gender: String?,
    @Embedded (prefix = "origin_") var origin: PlaceEntity?,
    @Embedded(prefix= "location_") var location: PlaceEntity?,
    var image: String?,
    var created: String?
)
