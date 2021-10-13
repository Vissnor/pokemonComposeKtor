package com.plcoding.jetpackcomposepokedex.di

import org.koin.androidx.viewmodel.dsl.viewModel
import com.plcoding.jetpackcomposepokedex.presentation.ui.pokemon.PokemonListViewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { PokemonListViewModel(get()) }
}