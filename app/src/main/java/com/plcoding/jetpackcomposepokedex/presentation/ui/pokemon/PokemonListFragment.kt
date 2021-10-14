package com.plcoding.jetpackcomposepokedex.presentation.ui.pokemon

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.plcoding.jetpackcomposepokedex.R
import com.plcoding.jetpackcomposepokedex.presentation.ui.theme.JetpackComposePokedexTheme
import com.plcoding.jetpackcomposepokedex.utils.base.findRootNavController
import org.koin.android.ext.android.inject
import org.koin.androidx.compose.getViewModel

class PokemonListFragment : Fragment() {


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
                JetpackComposePokedexTheme(darkTheme = true) {
                    PokemonListScreen(
                        viewModel = getViewModel(),
                        navController = findNavController()
                    )
                }
            }
        }
    }

    @Composable
    fun PokemonListContent(
        viewModel: PokemonListViewModel,
        rootNavController: NavController,
        navController: NavController
    ) {

    }
}