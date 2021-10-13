package com.plcoding.jetpackcomposepokedex.data.network

import com.plcoding.jetpackcomposepokedex.data.network.response.PokemonList
import io.ktor.client.HttpClient
import io.ktor.client.request.get

private const val BASE_URL = "https://pokeapi.co/api/v2"

class PokemonApi(private val client: HttpClient) {
    suspend fun getPokemonList(): PokemonList = client.get("$BASE_URL/pokemon")

    suspend fun getPokemon(name: String): PokemonList = client.get("$BASE_URL/pokemon/$name")

//    suspend fun getPokemon(user: UserEntity) {
//        client.post<UserEntity>("$END_POINT_POST_USER_KTOR") {
//            body = user
//        }
//    }
}