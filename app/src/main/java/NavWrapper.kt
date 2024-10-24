package com.jleon.macropayhometest
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.firebase.auth.FirebaseAuth
import com.jleon.macropayhometest.com.jleon.macropayhometest.ui.presentation.LoginScreen
import com.jleon.macropayhometest.com.jleon.macropayhometest.ui.presentation.PokemonDetailsScreen
import com.jleon.macropayhometest.com.jleon.macropayhometest.ui.presentation.PokemonListScreen

@Composable
fun NavWrapper(
    navHostController: NavHostController,
    auth: FirebaseAuth
) {

    NavHost(navController = navHostController, startDestination = "logIn") {
        composable("pokemonList") {
            PokemonListScreen(navHostController) {
                navHostController.navigate("pokemonDetails")
            }
        }
        composable("pokemonDetails"){
            PokemonDetailsScreen(navHostController)
        }
        composable("logIn") {
            LoginScreen(auth) { navHostController.navigate("pokemonList") }
        }
    }
}

