package zed.rainxch.februaryminichallenges.last_active.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import zed.rainxch.februaryminichallenges.last_active.domain.repository.LastActiveRepository

class LastActiveRepositoryImpl (
    private val dataStore: DataStore<Preferences>
) : LastActiveRepository {
    override fun getLastActive(): Flow<Long?> {
        return dataStore.data.map { preferences ->
            preferences[LAST_ACTIVE]
        }
    }

    override suspend fun setLastActive(lastActiveMillis: Long) {
        dataStore.edit { preferences ->
            preferences[LAST_ACTIVE] = lastActiveMillis
        }
    }

    private companion object {
        private val LAST_ACTIVE = longPreferencesKey("last_active")
    }
}