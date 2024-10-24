package com.jleon.macropayhometest.com.jleon.macropayhometest.ui.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import com.jleon.macropayhometest.data.model.PokemonInfo
import com.jleon.macropayhometest.ui.theme.pokedexRed
import java.util.Locale

@Composable
fun PokemonListScreen(navController: NavController, navigateToPokemonDetails:() -> Unit) {
    val viewModel: MainViewModel by lazy {
        ViewModelProvider(navController.getViewModelStoreOwner(0))[MainViewModel::class.java]
    }

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column {
            AppBar("Pokedex", navController)
            Text("Welcome to the Pokedex, tap on a pokemon to learn more about it.", fontSize = 20.sp,  modifier = Modifier.padding(8.dp), textAlign = TextAlign.Center)
            Spacer(modifier = Modifier.height(8.dp))
            PokemonList(viewModel, navController)
        }
    }
}


@Composable
fun PokemonList(viewModel: MainViewModel, navController: NavController) {
    val pokemonList by remember { viewModel.pokemonList }
    val endReached by remember { viewModel.endReached }
    val loadError by remember { viewModel.loadError }
    val isLoading by remember { viewModel.isLoading }

    LazyColumn(contentPadding = PaddingValues(16.dp)) {
        val itemCount = pokemonList.size

        items(itemCount) {
            if(it >= itemCount - 1 && !endReached) {
                viewModel.loadPokemon()
            }

            PokedexRow(rowIndex = it, entries = pokemonList, navController = navController, viewModel)
        }
    }

    Box(
        contentAlignment = Center,
        modifier = Modifier.fillMaxSize()
    ) {
        if(isLoading) {
            CircularProgressIndicator(color = pokedexRed)
        }
        if(loadError.isNotEmpty()) {
                viewModel.loadPokemon()
        }
    }

}


@Composable
fun PokedexRow(
    rowIndex: Int,
    entries: List<PokemonInfo>,
    navController: NavController,
    viewModel: MainViewModel
) {
    Column {
        Card(onClick = {
            viewModel.loadPokemonDetails(entries[rowIndex].url)
            navController.navigate("pokemonDetails")
        }, modifier = Modifier.wrapContentHeight()
            .padding(8.dp)) {
            Text(
                text = "${rowIndex+1}. ${entries[rowIndex].name.capitalize(Locale.ROOT)}",
                fontSize = 20.sp,
                textAlign = TextAlign.Start,
                modifier = Modifier.fillMaxWidth().padding(8.dp)
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
    }
}
