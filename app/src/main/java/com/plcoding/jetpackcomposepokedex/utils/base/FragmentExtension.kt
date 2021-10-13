package com.plcoding.jetpackcomposepokedex.utils.base

import android.content.Context
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.plcoding.jetpackcomposepokedex.R

fun Fragment.showKeyboard() {
    val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, 0)
}

fun Fragment.hideKeyboard() {
    this@hideKeyboard.activity?.let { activity ->
        val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(this@hideKeyboard.view?.windowToken, 0)
    }
}

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