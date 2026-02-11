package zed.rainxch.februaryminichallenges.utils

import android.content.BroadcastReceiver
import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.core.content.getSystemService

object ApplicationContextHolder {
    private var applicationContext: Context? = null

    val connectivityManager: ConnectivityManager by lazy {
        applicationContext?.getSystemService<ConnectivityManager>()
            ?: throw IllegalStateException("init function is not called :)")
    }

    val contentResolver: ContentResolver by lazy {
        applicationContext?.contentResolver
            ?: throw IllegalStateException("init function is not called :)")
    }

    val notificationManager: NotificationManagerCompat by lazy {
        NotificationManagerCompat.from(
            applicationContext ?: throw IllegalStateException("init function is not called :)")
        )
    }
    val packageName: String by lazy {
        (applicationContext ?: throw IllegalStateException("init function is not called :)")).packageName
    }

    fun startActivity(intent: Intent) {
        applicationContext?.startActivity(intent)
    }

    fun registerBroadcast(
        receiver: BroadcastReceiver,
        intentFilter: IntentFilter
    ) {
        ContextCompat.registerReceiver(
            applicationContext ?: throw IllegalStateException("init function is not called :)"),
            receiver,
            intentFilter,
            ContextCompat.RECEIVER_NOT_EXPORTED
        )
    }

    fun init(context: Context) {
        applicationContext = context.applicationContext
    }

    fun unregisterReceiver(receiver: BroadcastReceiver) {
        applicationContext?.unregisterReceiver(receiver)
    }
}