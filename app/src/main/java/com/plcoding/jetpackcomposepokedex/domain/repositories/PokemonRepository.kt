package com.plcoding.jetpackcomposepokedex.domain.repositories

import com.plcoding.jetpackcomposepokedex.data.network.NetworkHandler
import com.plcoding.jetpackcomposepokedex.data.network.PokemonApi
import com.plcoding.jetpackcomposepokedex.data.network.response.PokemonList

class PokemonRepository(
    private val networkHandler: NetworkHandler,
    private val pokemonApi: PokemonApi
) {
    suspend fun getPokemons() : PokemonList? {
        return if(networkHandler.isConnected) {
            try {
                pokemonApi.getPokemonList()
            } catch(e: Exception) {
                null
            }
        } else {
            null
        }
    }
}