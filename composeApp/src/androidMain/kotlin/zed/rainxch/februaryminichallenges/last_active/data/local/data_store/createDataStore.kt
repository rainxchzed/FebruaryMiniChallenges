package zed.rainxch.februaryminichallenges.last_active.data.local.data_store

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import zed.rainxch.februaryminichallenges.utils.ApplicationContextHolder

fun createDataStore(): DataStore<Preferences> = createDataStore(
    producePath = { ApplicationContextHolder.filesDir.resolve(dataStoreFileName).absolutePath }
)
