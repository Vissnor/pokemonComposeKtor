package com.plcoding.jetpackcomposepokedex.di

import com.plcoding.jetpackcomposepokedex.domain.repositories.PokemonRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { PokemonRepository(get(), get()) }
}