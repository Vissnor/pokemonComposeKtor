package com.plcoding.jetpackcomposepokedex.presentation.ui.pokemon

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewModelScope
import androidx.palette.graphics.Palette
import com.plcoding.jetpackcomposepokedex.data.network.models.PokemonList
import com.plcoding.jetpackcomposepokedex.domain.models.PokedexListEntry
import com.plcoding.jetpackcomposepokedex.domain.repositories.PokemonRepository
import com.plcoding.jetpackcomposepokedex.presentation.base.BaseViewModel
import com.plcoding.jetpackcomposepokedex.utils.base.SingleEventWithContent
import com.plcoding.jetpackcomposepokedex.utils.network.Resource
import kotlinx.coroutines.launch
import java.util.*

const val PAGE_SIZE = 20

class PokemonListViewModel(
    private val pokemonRepository: PokemonRepository
) : BaseViewModel() {

    private var currentPage = 0

    var pokemonList = mutableStateOf(listOf<PokedexListEntry>())
    var loadError = mutableStateOf("")
    var isLoading = mutableStateOf(false)
    var endReached = mutableStateOf(false)

    val navigateToDetail = SingleEventWithContent<Pair<String, Int>>()

    fun onItemClicked(name: String, dominantColor: Int) = runAsync {
        navigateToDetail.send(Pair(name, dominantColor))
    }

    init {
        loadPokemonPaginated()
    }

    fun loadPokemonPaginated() = viewModelScope.launch {
        val result = pokemonRepository.getAllPokemonPaginated(PAGE_SIZE, currentPage * PAGE_SIZE)
        when(result) {
            is Resource.Success -> {
                endReached.value = currentPage * PAGE_SIZE >= result.data!!.count
                val pokedexEntries = mapPokedexEntriesWithNumber(result.data.results)
                currentPage++

                loadError.value = ""
                isLoading.value = false
                pokemonList.value = pokedexEntries

            }
            is Resource.Error -> {
                loadError.value = result.message!!
                isLoading.value = false
            }
            else -> {}
        }
    }

    private fun mapPokedexEntriesWithNumber(entries: List<PokemonList.Result>): List<PokedexListEntry> {
        return entries.map { entry ->
            val number = if (entry.url.endsWith("/")) {
                entry.url.dropLast(1).takeLastWhile { it.isDigit() }
            } else {
                entry.url.takeLastWhile { it.isDigit() }
            }
            val url =
                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${number}.png"
            PokedexListEntry(entry.name.capitalize(Locale.ROOT), url, number.toInt())
        }
    }

    fun calcDominantColor(drawable: Drawable, onFinish: (Color) -> Unit) {
        val bmp = (drawable as BitmapDrawable).bitmap.copy(Bitmap.Config.ARGB_8888, true)
        Palette.from(bmp).generate{ palette ->
            palette?.dominantSwatch?.rgb?.let {
                onFinish(Color(it))
            }
        }
    }
}