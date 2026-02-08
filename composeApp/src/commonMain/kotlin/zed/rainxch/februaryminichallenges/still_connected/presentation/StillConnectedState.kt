package zed.rainxch.februaryminichallenges.still_connected.presentation

import zed.rainxch.februaryminichallenges.still_connected.domain.model.ConnectivityState

data class StillConnectedState(
    val connectivityState: ConnectivityState = ConnectivityState.Connected
)