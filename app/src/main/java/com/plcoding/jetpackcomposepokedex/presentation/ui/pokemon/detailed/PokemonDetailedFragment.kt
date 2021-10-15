package com.plcoding.jetpackcomposepokedex.presentation.ui.pokemon.detailed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.plcoding.jetpackcomposepokedex.presentation.ui.theme.JetpackComposePokedexTheme
import com.plcoding.jetpackcomposepokedex.utils.base.navController
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class PokemonDetailedFragment : Fragment() {
    private val args: PokemonDetailedFragmentArgs by navArgs()
    private val viewModel: PokemonDetailedViewModel by viewModel { parametersOf(args)}

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
                    PokemonDetailedScreen(
                        viewModel = viewModel,
                        navController = navController
                    )
                }
            }
        }
    }
}