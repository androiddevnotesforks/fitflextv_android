package com.dreamsoftware.fitflextv.utils.network

import android.net.ConnectivityManager
import android.net.Network
import com.dreamsoftware.fitflextv.utils.AppEvent
import com.dreamsoftware.fitflextv.utils.AppEventBus

class NetworkConnectivityCallback(private val appEventBus: AppEventBus) : ConnectivityManager.NetworkCallback() {

    private var lastState: Boolean = true

    override fun onAvailable(network: Network) {
        super.onAvailable(network)
        updateConnectivityState(true)
    }

    override fun onLost(network: Network) {
        super.onLost(network)
        updateConnectivityState(false)
    }

    private fun updateConnectivityState(newState: Boolean) {
        if (newState != lastState) {
            appEventBus.send(
                AppEvent.NetworkConnectivityStateChanged(
                    lastState = lastState,
                    newState = newState
                )
            ).also {
                lastState = newState
            }
        }
    }

    companion object {
        private const val TAG = "NetworkConnectivity"
    }
}