package com.example.pokedex.presentation.listpresentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.pokedex.R
import com.example.pokedex.presentation.components.PokemonItem


@Composable
fun PokemonListScreen(
    viewModel: PokemonListViewModel = hiltViewModel(),
    onNavigateToDetails: (name: String) -> Unit,
) {
    val pokemonList = viewModel.pokemonPager.collectAsLazyPagingItems()

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding->
        Column(
            modifier=  Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            if (pokemonList.loadState.refresh == LoadState.Loading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            } else {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.pokeicon),
                        contentDescription = "Pokédex",
                        modifier = Modifier.size(75.dp)
                    )

                    Text(
                        text = "Pokédex",
                        fontWeight = FontWeight.Light,
                        fontSize = 30.sp,
                        color = Color.Gray,
                        maxLines = 1,
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                }

                LazyColumn(
                    modifier= Modifier,
                    horizontalAlignment= Alignment.CenterHorizontally
                ){
                    items(
                        count = pokemonList.itemCount,
                        key = {
                            pokemonList[it]?.pokemonBasicInfoDomain?.name ?: it
                        }
                    ){item ->
                        pokemonList[item]?.let {pokemon->
                            PokemonItem(
                                pokemon = pokemon,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp)
                                    .clickable {
                                        onNavigateToDetails(pokemon.pokemonBasicInfoDomain.name)
                                    }
                            )
                            if (item < pokemonList.itemCount) {
                                HorizontalDivider(
                                    modifier = Modifier
                                        .padding(horizontal = 16.dp)
                                )
                            }
                        }
                    }
                }
                if(pokemonList.loadState.append is LoadState.Loading) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .padding(vertical= 10.dp)
                            .align(Alignment.CenterHorizontally)
                            .scale(0.5f)
                            .weight(1f)
                    )
                }
            }
        }
    }

}

