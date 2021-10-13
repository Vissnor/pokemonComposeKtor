package com.plcoding.jetpackcomposepokedex.data.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.core.content.ContextCompat

class NetworkHandler(
    private val context: Context
) {
    val isConnected: Boolean
        get() {
            val connectivityManager =
                ContextCompat.getSystemService(context, ConnectivityManager::class.java)
            val capabilities = connectivityManager?.let {
                it.getNetworkCapabilities(it.activeNetwork)
            }
            val hasTransportCapabilities = capabilities?.let {
                it.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                        it.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                        it.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
            }
            return hasTransportCapabilities ?: false
        }
}