package com.example.pokedex.presentation.detailspresentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.pokedex.domain.repository.PokemonRepository
import com.example.pokedex.navigation.Screens
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonDetailsViewModel @Inject constructor(
    private val repository: PokemonRepository,
    stateHandle: SavedStateHandle,
): ViewModel() {

    private val _pokemonDetailsState = MutableStateFlow(PokemonDetailsState())
    val pokemonDetailsState : StateFlow<PokemonDetailsState> by lazy {
        getBreedDetailsByName(stateHandle.toRoute<Screens.PokemonDetailsScreen>().name)
        _pokemonDetailsState
    }

    private fun getBreedDetailsByName(name: String) {
        viewModelScope.launch {
            repository.getPokemonInfoScreen(name).onSuccess { pokemon ->
                _pokemonDetailsState.update {
                    it.copy(pokemonDetails = pokemon)
                }
            }
        }
    }
}