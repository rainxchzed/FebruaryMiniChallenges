package zed.rainxch.februaryminichallenges.still_connected.data.service

import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import platform.Network.nw_interface_type_cellular
import platform.Network.nw_interface_type_wifi
import platform.Network.nw_interface_type_wired
import platform.Network.nw_path_get_status
import platform.Network.nw_path_monitor_cancel
import platform.Network.nw_path_monitor_create
import platform.Network.nw_path_monitor_set_queue
import platform.Network.nw_path_monitor_set_update_handler
import platform.Network.nw_path_monitor_start
import platform.Network.nw_path_status_satisfied
import platform.Network.nw_path_uses_interface_type
import platform.darwin.dispatch_queue_attr_make_with_qos_class
import platform.darwin.dispatch_queue_create
import platform.posix.QOS_CLASS_BACKGROUND
import zed.rainxch.februaryminichallenges.still_connected.domain.ConnectivityManager
import zed.rainxch.februaryminichallenges.still_connected.domain.model.ConnectivityState

@OptIn(ExperimentalForeignApi::class)
class IOSConnectivityManager : ConnectivityManager {
    override fun connectivityStateFlow(): Flow<ConnectivityState> = callbackFlow {
        val monitor = nw_path_monitor_create()

        if (monitor == null) {
            trySend(ConnectivityState.Disconnected)
            close()
            return@callbackFlow
        }

        // Create queue with proper type
        val queueAttr = dispatch_queue_attr_make_with_qos_class(
            null,
            QOS_CLASS_BACKGROUND,
            0
        )
        val queue = dispatch_queue_create("com.app.networkmonitor", queueAttr)

        nw_path_monitor_set_update_handler(monitor) { path ->
            if (path != null) {
                val status = nw_path_get_status(path)
                val state = when (status) {
                    nw_path_status_satisfied -> {
                        // Double-check it has internet capability
                        val hasInternet = nw_path_uses_interface_type(path, nw_interface_type_wifi) ||
                                nw_path_uses_interface_type(path, nw_interface_type_cellular) ||
                                nw_path_uses_interface_type(path, nw_interface_type_wired)

                        if (hasInternet) ConnectivityState.Connected
                        else ConnectivityState.Disconnected
                    }
                    else -> ConnectivityState.Disconnected
                }
                trySend(state)
            }
        }

        nw_path_monitor_set_queue(monitor, queue)
        nw_path_monitor_start(monitor)

        awaitClose {
            nw_path_monitor_cancel(monitor)
        }
    }

}