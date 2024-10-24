package com.jleon.macropayhometest.data.model

import com.jleon.macropayhometest.data.model.PokemonInfo

data class PokemonListResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<PokemonInfo>
)


