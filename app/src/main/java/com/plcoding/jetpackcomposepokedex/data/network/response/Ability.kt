package com.plcoding.jetpackcomposepokedex.data.network.response

data class Ability(
    val ability: AbilityX,
    val is_hidden: Boolean,
    val slot: Int
)