package zed.rainxch.februaryminichallenges.missed_messages.data

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.provider.Settings
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.ProcessLifecycleOwner
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.withContext
import zed.rainxch.februaryminichallenges.missed_messages.domain.MissedMessagedRepository
import zed.rainxch.februaryminichallenges.utils.ApplicationContextHolder

class AndroidMissedMessagedRepository : MissedMessagedRepository {
    override fun notificationEnabledFlow(): Flow<Boolean> = callbackFlow {
        val manager = ApplicationContextHolder.notificationManager

        trySend(manager.areNotificationsEnabled())

        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                trySend(manager.areNotificationsEnabled())
            }
        }

        val lifecycle = ProcessLifecycleOwner.get().lifecycle
        withContext(Dispatchers.Main) {
            lifecycle.addObserver(observer)
        }

        awaitClose {
            lifecycle.removeObserver(observer)
        }
    }

    override fun openNotificationSettings() {
        val intent = Intent()
        intent.setAction(Settings.ACTION_APP_NOTIFICATION_SETTINGS)
        intent.putExtra(Settings.EXTRA_APP_PACKAGE, ApplicationContextHolder.packageName)
        intent.addFlags(FLAG_ACTIVITY_NEW_TASK)
        ApplicationContextHolder.startActivity(intent)
    }

}