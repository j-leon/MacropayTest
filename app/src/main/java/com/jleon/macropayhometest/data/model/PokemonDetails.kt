package com.jleon.macropayhometest.data.model

import com.google.gson.annotations.SerializedName

data class PokemonDetails(
    val id: Int,
    val name: String,
    val height: Int,
    val weight: Int,
    val types: List<Type>?,
    @SerializedName("sprites")
    val sprites: Sprite?,
)
data class Type(
    val slot: Int,
    val type: TypeInfo
)

data class TypeInfo(
    val name: String
)

data class Sprite(
    val other: Other
)

data class Other(
    @SerializedName("official-artwork")
    val officialArtwork: OfficialArtwork
)

data class OfficialArtwork(
    @SerializedName("front_default")
    val imageUrl: String
)
