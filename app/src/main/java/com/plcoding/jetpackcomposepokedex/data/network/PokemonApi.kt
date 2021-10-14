package com.plcoding.jetpackcomposepokedex.data.network

import com.plcoding.jetpackcomposepokedex.data.network.models.Pokemon
import com.plcoding.jetpackcomposepokedex.data.network.models.PokemonList
import io.ktor.client.HttpClient
import io.ktor.client.request.*

class PokemonApi(private val client: HttpClient) {
    suspend fun getPokemonList(
        offset: Int, limit: Int
    ): PokemonList = client.get("pokemon") {
        parameter("offset", offset)
        parameter("limit", limit)
    }

    suspend fun getPokemon(name: String): Pokemon = client.get("pokemon/$name")

//    suspend fun getPokemon(user: UserEntity) {
//        client.post<UserEntity>("$END_POINT_POST_USER_KTOR") {
//            body = user
//        }
//    }
}