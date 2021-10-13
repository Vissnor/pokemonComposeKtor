package com.plcoding.jetpackcomposepokedex.di

import com.plcoding.jetpackcomposepokedex.data.network.NetworkHandler
import com.plcoding.jetpackcomposepokedex.data.network.PokemonApi
import com.plcoding.jetpackcomposepokedex.data.network.createKtor
import org.koin.dsl.module

val networkModule = module {
    single { createKtor() }
    single { NetworkHandler(get()) }
    single { PokemonApi(get()) }
}