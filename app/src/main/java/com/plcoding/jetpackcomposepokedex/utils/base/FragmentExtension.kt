package com.plcoding.jetpackcomposepokedex.utils.base

import android.view.WindowManager
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.plcoding.jetpackcomposepokedex.R

fun Fragment.findRootNavController() = requireActivity().findNavController(R.id.main_host_fragment)

val Fragment.navController
    get() = findNavController()

fun Fragment.navigate(directions: NavDirections) = navController.navigate(directions)

fun Fragment.colorStatusBar(@ColorRes color: Int) {
    val window = requireActivity().window
    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
    window.statusBarColor = ContextCompat.getColor(requireContext(), color)
}

fun Fragment.navigateFromRoot(directions: NavDirections, navOptions: NavOptions? = null) {
    requireActivity().findNavController(R.id.main_host_fragment).navigate(directions, navOptions)
}