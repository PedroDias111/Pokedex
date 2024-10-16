package com.example.pokedex.presentation.listpresentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.pokedex.data.mapper.toPokemonInfo
import com.example.pokedex.domain.repository.PokemonRepository
import com.example.myapplication.util.Constants.PAGE_SIZE
import com.example.pokedex.data.remote.PokemonRemoteMediator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val repository: PokemonRepository,
    private val remoteMediator: PokemonRemoteMediator
): ViewModel() {

    @OptIn(ExperimentalPagingApi::class)
    val pokemonPager = Pager(
        config = PagingConfig(
            pageSize = PAGE_SIZE,
            enablePlaceholders = true,
            prefetchDistance = 3 * PAGE_SIZE,
            initialLoadSize = 2 * PAGE_SIZE,
        ),
        remoteMediator = remoteMediator,
        pagingSourceFactory = {
            repository.getPagingSource()
        }
    ).flow
        .map {pagingData ->
            pagingData.map {
                it.toPokemonInfo()
            }
        }
        .cachedIn(viewModelScope)
}