package com.plcoding.jetpackcomposepokedex.presentation.ui.pokemon.detailed

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewModelScope
import com.plcoding.jetpackcomposepokedex.data.network.models.Pokemon
import com.plcoding.jetpackcomposepokedex.domain.repositories.PokemonRepository
import com.plcoding.jetpackcomposepokedex.presentation.base.BaseViewModel
import com.plcoding.jetpackcomposepokedex.utils.network.Resource
import kotlinx.coroutines.launch

class PokemonDetailedViewModel(
    private val pokemonRepository: PokemonRepository,
    argsPokemonName: String,
    argsDominantColor: Int
): BaseViewModel() {
    private var pokemonName = mutableStateOf(argsPokemonName)
    var dominantColor = mutableStateOf<Color?>(Color(argsDominantColor))
    var pokemon = mutableStateOf<Pokemon?>(null)
    var loadError = mutableStateOf("")
    var isLoading = mutableStateOf(false)

    init {
        getPokemonInfo()
    }

    private fun getPokemonInfo() = viewModelScope.launch {
        val result = pokemonRepository.getPokemonInfo(pokemonName.value)
        when(result) {
            is Resource.Success -> {
                pokemon.value = result.data
                isLoading.value = false
            }
            is Resource.Error -> {
                loadError.value = result.message ?: ""
                isLoading.value = false
            }
            else -> {}
        }
    }
}