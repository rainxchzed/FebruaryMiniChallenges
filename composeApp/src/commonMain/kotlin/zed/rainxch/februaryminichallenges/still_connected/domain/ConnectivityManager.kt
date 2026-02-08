package zed.rainxch.februaryminichallenges.still_connected.domain

import kotlinx.coroutines.flow.Flow
import zed.rainxch.februaryminichallenges.still_connected.domain.model.ConnectivityState

interface ConnectivityManager {
    fun connectivityStateFlow(): Flow<ConnectivityState>
}