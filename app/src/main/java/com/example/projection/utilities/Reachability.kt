package com.example.projection.utilities

import android.content.Context
import android.net.ConnectivityManager
import android.os.Build

object Reachability {

    fun isInternetConnected(getApplicationContext: Context): Boolean {
        var status = false

        val service = Context.CONNECTIVITY_SERVICE
        val manager = getApplicationContext.getSystemService(service) as ConnectivityManager?

        if (manager != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (manager.activeNetwork != null
                    && manager.getNetworkCapabilities(manager.activeNetwork) != null) {
                    status = true
                }
            } else {
                // Fallback on deprecated API for older devices
                if (manager.activeNetworkInfo != null
                    && manager.activeNetworkInfo!!.isConnectedOrConnecting) {
                    status = true
                }
            }
        }
        return status
    }
}
