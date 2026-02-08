package zed.rainxch.februaryminichallenges.still_connected.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import zed.rainxch.februaryminichallenges.still_connected.domain.ConnectivityManager

class StillConnectedViewModel(
    private val connectivityManager: ConnectivityManager
) : ViewModel() {

    private var hasLoadedInitialData = false

    private val _state = MutableStateFlow(StillConnectedState())
    val state = _state
        .onStart {
            if (!hasLoadedInitialData) {
                observeConnectivityState()

                hasLoadedInitialData = true
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = StillConnectedState()
        )

    private fun observeConnectivityState() {
        viewModelScope.launch {
            connectivityManager.connectivityStateFlow().collect { connectivityState ->
                _state.update {
                    it.copy(
                        connectivityState = connectivityState
                    )
                }
            }
        }
    }

}