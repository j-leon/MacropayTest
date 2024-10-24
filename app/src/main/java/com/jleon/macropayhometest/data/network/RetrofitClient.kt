package com.jleon.macropayhometest.data.network
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient private constructor() {
    companion object {
        private const val BASE_URL = "https://pokeapi.co/api/v2/"

        @Volatile
        private var instance: Retrofit? = null

        private fun getInstance(): Retrofit {
            return instance ?: synchronized(this) {
                instance ?: buildRetrofit().also { instance = it }
            }
        }

        private fun buildRetrofit(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        fun createPokemonService(): PokemonService {
            return getInstance().create(PokemonService::class.java)
        }
    }
}

