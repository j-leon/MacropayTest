package com.jleon.macropayhometest.com.jleon.macropayhometest.ui.presentation
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jleon.macropayhometest.PokemonRepository

import com.jleon.macropayhometest.data.model.PokemonDetails
import com.jleon.macropayhometest.data.model.PokemonInfo
import kotlinx.coroutines.launch

class MainViewModel() : ViewModel() {
    private val repository = PokemonRepository()
    private var offset = 0
    private val limit = 20
    val pokemonList = mutableStateOf<List<PokemonInfo>>(listOf())
    val pokemon = mutableStateOf<PokemonDetails>(PokemonDetails(0,"",0,0, listOf(), null))
    var loadError = mutableStateOf("")
    var isLoading = mutableStateOf(false)
    var endReached = mutableStateOf(false)

    init {
        loadPokemon()
    }

    fun loadPokemon() {
        isLoading = mutableStateOf(true)
        viewModelScope.launch {
            try {
                val response = repository.getPokemonList(offset, limit)
                pokemonList.value = listOf(pokemonList.value, response.results).flatten()
                offset += limit
            } catch (e: Exception) {
                // TODO: add error message for the user
                e.printStackTrace()
            }
            isLoading = mutableStateOf(false)
        }
    }

    fun loadPokemonDetails(url: String){
        viewModelScope.launch {
            try {
                val response = repository.getPokemonDetails(url)
                pokemon.value= response
            } catch (e: Exception){
                // TODO: add error message for the user
                e.printStackTrace()
            }
        }
    }
}