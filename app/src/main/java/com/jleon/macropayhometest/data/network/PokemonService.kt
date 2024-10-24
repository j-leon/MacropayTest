package com.jleon.macropayhometest.data.network
import com.jleon.macropayhometest.data.model.PokemonDetails
import com.jleon.macropayhometest.data.model.PokemonListResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface PokemonService {

    @GET("pokemon")
    suspend fun getPokemonList(@Query("offset") offset: Int, @Query("limit") limit: Int): Response<PokemonListResponse>

    @GET
    suspend fun getPokemonDetails(@Url url: String): Response<PokemonDetails>

}