package com.plcoding.jetpackcomposepokedex.domain.repositories

import android.accounts.NetworkErrorException
import com.plcoding.jetpackcomposepokedex.data.network.NetworkHandler
import com.plcoding.jetpackcomposepokedex.data.network.PokemonApi
import com.plcoding.jetpackcomposepokedex.data.network.models.Pokemon
import com.plcoding.jetpackcomposepokedex.data.network.models.PokemonList
import com.plcoding.jetpackcomposepokedex.utils.network.Resource

class PokemonRepository(
    private val networkHandler: NetworkHandler,
    private val pokemonApi: PokemonApi
) {
    suspend fun getAllPokemonPaginated(pageSize: Int, offset: Int) : Resource<PokemonList> {
        if(!networkHandler.isConnected)
            return Resource.Error(NetworkErrorException().message?: "Not connected")

        return try {
            Resource.Success(pokemonApi.getPokemonList(pageSize, offset))
        } catch(e: Exception) {
            Resource.Error(e.message?: "Unknown error")
        }
    }

    suspend fun getPokemon(name: String) : Resource<Pokemon> {
        if(!networkHandler.isConnected)
            return Resource.Error(NetworkErrorException().message?: "Not connected")

        return try {
            Resource.Success(pokemonApi.getPokemon(name))
        } catch(e: Exception) {
            Resource.Error(e.message?: "Unknown error")
        }
    }
}