package com.plcoding.jetpackcomposepokedex.presentation.ui.pokemon

import com.plcoding.jetpackcomposepokedex.domain.repositories.PokemonRepository
import com.plcoding.jetpackcomposepokedex.presentation.base.BaseViewModel

class PokemonListViewModel(
    pokemonRepository: PokemonRepository
) : BaseViewModel() {
    init {
        runAsync {
            pokemonRepository.getPokemons()
        }
    }
}