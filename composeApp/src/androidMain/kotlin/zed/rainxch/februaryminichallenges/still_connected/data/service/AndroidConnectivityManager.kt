package zed.rainxch.februaryminichallenges.still_connected.data.service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.LinkProperties
import android.net.Network
import android.net.NetworkCapabilities
import android.provider.Settings
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.onStart
import zed.rainxch.februaryminichallenges.still_connected.domain.ConnectivityManager
import zed.rainxch.februaryminichallenges.still_connected.domain.model.ConnectivityState
import zed.rainxch.februaryminichallenges.utils.ApplicationContextHolder

class AndroidConnectivityManager : ConnectivityManager {
    override fun connectivityStateFlow(): Flow<ConnectivityState> = combine(
        observeConnectivity()
            .onStart { emit(ConnectivityState.Disconnected) },
        airplaneModeFlow()
            .onStart { emit(isAirplaneModeOn()) }
    ) { networkState, isAirplaneOn ->
        if (isAirplaneOn) {
            ConnectivityState.Airplane
        } else {
            networkState
        }
    }

    fun observeConnectivity(): Flow<ConnectivityState> = callbackFlow {
        val connectivityManager = ApplicationContextHolder.connectivityManager

        fun hasInternet(capabilities: NetworkCapabilities?): Boolean {
            return capabilities?.hasCapability(
                NetworkCapabilities.NET_CAPABILITY_INTERNET
            ) == true &&
                    capabilities.hasCapability(
                        NetworkCapabilities.NET_CAPABILITY_VALIDATED
                    )
        }

        val callback = object : android.net.ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                trySend(ConnectivityState.Connected)
            }

            override fun onCapabilitiesChanged(
                network: Network,
                networkCapabilities: NetworkCapabilities
            ) {
                if (hasInternet(networkCapabilities)) {
                    trySend(ConnectivityState.Connected)
                } else {
                    trySend(ConnectivityState.Disconnected)
                }
            }

            override fun onLost(network: Network) {
                super.onLost(network)

                trySend(ConnectivityState.Disconnected)
            }
        }

        connectivityManager.registerDefaultNetworkCallback(callback)

        awaitClose {
            connectivityManager.unregisterNetworkCallback(callback)
        }
    }

    fun airplaneModeFlow(): Flow<Boolean> = callbackFlow {
        val receiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                val isOn = intent.getBooleanExtra("state", false)
                trySend(isOn)
            }
        }

        ApplicationContextHolder.registerBroadcast(
            receiver,
            IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED)
        )

        awaitClose {
            ApplicationContextHolder.unregisterReceiver(receiver)
        }
    }

    private fun isAirplaneModeOn(): Boolean =
        Settings.Global.getInt(
            ApplicationContextHolder.contentResolver,
            Settings.Global.AIRPLANE_MODE_ON,
            0
        ) == 1
}