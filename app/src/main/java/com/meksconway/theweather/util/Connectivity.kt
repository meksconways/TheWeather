package com.meksconway.theweather.util

import android.content.Context
import android.net.ConnectivityManager

interface Connectivity {

    fun hasNetworkAccess(): Boolean

}

class ConnectivityImpl(private val context: Context) : Connectivity {

    override fun hasNetworkAccess(): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val info = connectivityManager.activeNetwork
        return info != null
    }
}