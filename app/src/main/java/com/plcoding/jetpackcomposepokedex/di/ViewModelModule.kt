package com.plcoding.jetpackcomposepokedex.di

import org.koin.androidx.viewmodel.dsl.viewModel
import com.plcoding.jetpackcomposepokedex.presentation.ui.pokemon.PokemonListViewModel
import com.plcoding.jetpackcomposepokedex.presentation.ui.pokemon.detailed.PokemonDetailedFragmentArgs
import com.plcoding.jetpackcomposepokedex.presentation.ui.pokemon.detailed.PokemonDetailedViewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { PokemonListViewModel(get()) }
    viewModel { (args: PokemonDetailedFragmentArgs) ->
        PokemonDetailedViewModel(get(), argsDominantColor = args.dominantColor, argsPokemonName = args.pokemonName)
    }
}