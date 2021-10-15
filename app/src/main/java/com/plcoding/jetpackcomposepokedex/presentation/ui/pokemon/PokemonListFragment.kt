package com.plcoding.jetpackcomposepokedex.presentation.ui.pokemon

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.plcoding.jetpackcomposepokedex.presentation.ui.pokemon.detailed.PokemonDetailedViewModel
import com.plcoding.jetpackcomposepokedex.presentation.ui.theme.JetpackComposePokedexTheme
import com.plcoding.jetpackcomposepokedex.utils.base.navController
import org.koin.androidx.compose.getViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class PokemonListFragment : Fragment() {
    private val viewModel: PokemonListViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(
                ViewCompositionStrategy.DisposeOnLifecycleDestroyed(viewLifecycleOwner)
            )
            setContent {
                JetpackComposePokedexTheme(darkTheme = false) {
                    PokemonListScreen(
                        viewModel = getViewModel(),
                        navController = findNavController()
                    )
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.navigateToDetail.collect(viewLifecycleOwner) {
            navController.navigate(
                PokemonListFragmentDirections.actionPokemonListFragmentToPokemonDetailedFragment(
                    it.first,
                    it.second
                )
            )
        }
    }
}