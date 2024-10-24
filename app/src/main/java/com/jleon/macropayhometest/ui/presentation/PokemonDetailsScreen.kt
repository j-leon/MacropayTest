package com.jleon.macropayhometest.com.jleon.macropayhometest.ui.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.jleon.macropayhometest.data.model.PokemonDetails
import java.util.Locale

@Composable
fun PokemonDetailsScreen(navController: NavController) {
    val viewModel: MainViewModel by lazy {
        ViewModelProvider(navController.getViewModelStoreOwner(0))[MainViewModel::class.java]
    }

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column {
            AppBar("Details of ${viewModel.pokemon.value.name.capitalize(Locale.ROOT)}", navController = navController)
            PokemonDetails(viewModel.pokemon.value)
        }
    }
}

@Composable
fun PokemonDetails(pokemonDetails: PokemonDetails) {
    Column {
        AsyncImage(model = pokemonDetails.sprites?.other?.officialArtwork?.imageUrl,
            contentDescription = pokemonDetails.name.capitalize(Locale.ROOT), modifier = Modifier
                .size(120.dp)
                .align(CenterHorizontally))
        Text(
            text = pokemonDetails.name.capitalize(Locale.ROOT),
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        )
        Text(
            text = "Types: ${pokemonDetails.types?.joinToString {it.type.name }}",
            fontSize = 20.sp,
            textAlign = TextAlign.Start,
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        )
        Text(
            text = "Height: ${pokemonDetails.height}in",
            fontSize = 20.sp,
            textAlign = TextAlign.Start,
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        )
        Text(
            text = "Weight: ${pokemonDetails.weight} pounds.",
            fontSize = 20.sp,
            textAlign = TextAlign.Start,
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        )
    }
}
