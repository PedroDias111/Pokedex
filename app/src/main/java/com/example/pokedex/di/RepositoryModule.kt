package com.example.pokedex.di

import com.example.pokedex.data.repository.PokemonRepositoryImplementation
import com.example.pokedex.domain.repository.PokemonRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindPokemonRepository(
        pokemonRepository: PokemonRepositoryImplementation
    ): PokemonRepository
}