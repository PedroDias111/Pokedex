package com.example.pokedex.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        PokemonBasicInfoEntity::class,
        CriesEntity::class,
        SpeciesEntity::class,
        StatEntity::class,
        TypeEntity::class,
        AbilityEntity::class,
        RemoteKeys::class
    ],
    version = 1
)
abstract class PokemonDatabase: RoomDatabase() {
    abstract val dao: PokemonDao
    abstract fun remoteKeysDao(): RemoteKeysDao
}