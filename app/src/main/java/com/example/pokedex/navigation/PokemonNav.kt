package com.example.pokedex.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pokedex.presentation.detailspresentation.PokemonDetailsScreen
import com.example.pokedex.presentation.listpresentation.PokemonListScreen

@Composable
fun PokemonNav() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screens.PokemonListScreen,
    ){
        composable<Screens.PokemonListScreen>{
            PokemonListScreen(
                onNavigateToDetails = { name ->
                    navController.navigate(
                        Screens.PokemonDetailsScreen(name = name)
                    )
                },
            )
        }

        composable<Screens.PokemonDetailsScreen> {
            PokemonDetailsScreen(
                onNavigateBack = {
                    navController.navigateUp()
                }
            )
        }
    }
}

