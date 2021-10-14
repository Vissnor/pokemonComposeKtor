package com.plcoding.jetpackcomposepokedex.data.network

import android.accounts.NetworkErrorException
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.core.content.ContextCompat
import com.plcoding.jetpackcomposepokedex.data.network.models.Pokemon
import com.plcoding.jetpackcomposepokedex.utils.network.Resource

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