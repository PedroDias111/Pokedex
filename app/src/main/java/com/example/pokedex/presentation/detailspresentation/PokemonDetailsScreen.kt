package com.example.pokedex.presentation.detailspresentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.pokedex.presentation.components.PokemonItem
import com.example.pokedex.presentation.detailspresentation.components.AbilitiesCard
import com.example.pokedex.presentation.detailspresentation.components.SpeciesScreen
import com.example.pokedex.presentation.detailspresentation.components.StatsScreen


@Composable
fun PokemonDetailsScreen(
    viewModel: PokemonDetailsViewModel = hiltViewModel(),
    onNavigateBack: () -> Unit
) {
    val detailState = viewModel.pokemonDetailsState.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) { innerpadding->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerpadding),
            contentPadding = PaddingValues(16.dp)
        ){
            item {

                //Basic Pokemon Card (ID NAME TYPES IMAGE)

                PokemonItem(
                    detailState.value.pokemonDetails,
                    modifier = Modifier
                        .fillMaxWidth()
                )

                //Text Saying Species
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    horizontalArrangement = Arrangement.Center,
                ){
                    Text(
                        text= "Species",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Light
                    )
                }

                //Card with Height Weight cries
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                ){
                    detailState.value.pokemonDetails.cries?.let {
                        SpeciesScreen(
                            height = detailState.value.pokemonDetails.pokemonBasicInfoDomain.height,
                            weight = detailState.value.pokemonDetails.pokemonBasicInfoDomain.weight,
                            cries = it,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }

                //Abilities
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    horizontalArrangement = Arrangement.Center,
                ){
                    Text(
                        text= "Abilities",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Light
                    )
                }

                //Card with Abilities
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                ){
                    AbilitiesCard(
                        abilities = detailState.value.pokemonDetails.abilities,
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                //Stats

                //Abilities
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    horizontalArrangement = Arrangement.Center,
                ){
                    Text(
                        text= "Base Stats",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Light
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                ){
                    StatsScreen(
                        stats = detailState.value.pokemonDetails.stats,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}
