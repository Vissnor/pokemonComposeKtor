package com.plcoding.jetpackcomposepokedex.data.network.response

data class PokemonList(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<Result>
)