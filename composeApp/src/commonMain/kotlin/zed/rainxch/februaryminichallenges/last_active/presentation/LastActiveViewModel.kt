package zed.rainxch.februaryminichallenges.last_active.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import zed.rainxch.februaryminichallenges.last_active.domain.repository.LastActiveRepository
import kotlin.time.Clock

class LastActiveViewModel (
    private val lastActiveRepository: LastActiveRepository
) : ViewModel() {

    private var isInitiated = false

    private val _state = MutableStateFlow(LastActiveState())
    val state = _state
        .onStart {
            if(!isInitiated) {
                observeLastActive()

                isInitiated = true
            }
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            LastActiveState()
        )

    private fun observeLastActive() {
        viewModelScope.launch {
            lastActiveRepository.getLastActive().collect { lastActive ->
                _state.update { it.copy(
                    lastActiveMillis = lastActive
                ) }
            }
        }
    }

    fun onAction(action: LastActiveAction) {
        when (action) {
            LastActiveAction.OnAppOnBackground -> {
                lastActiveRepository.setLastActive(Clock.System.now().toEpochMilliseconds())
            }
        }
    }

}