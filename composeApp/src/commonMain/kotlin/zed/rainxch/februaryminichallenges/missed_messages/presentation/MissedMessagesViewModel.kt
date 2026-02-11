package zed.rainxch.februaryminichallenges.missed_messages.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import zed.rainxch.februaryminichallenges.missed_messages.domain.MissedMessagedRepository

class MissedMessagesViewModel(
    private val missedMessagedRepository: MissedMessagedRepository
) : ViewModel() {

    private var hasLoadedInitialData = false

    private val _state = MutableStateFlow(MissedMessagesState())
    val state = _state
        .onStart {
            if (!hasLoadedInitialData) {
                observeNotificationEnabled()

                hasLoadedInitialData = true
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = MissedMessagesState()
        )

    private fun observeNotificationEnabled() {
        viewModelScope.launch {
            missedMessagedRepository
                .notificationEnabledFlow()
                .flowOn(Dispatchers.IO)
                .collect { isNotificationEnabled ->
                    _state.update {
                        it.copy(
                            isNotificationEnabled = isNotificationEnabled
                        )
                    }
                }
        }
    }

    fun onAction(action: MissedMessagesAction) {
        when (action) {
            MissedMessagesAction.OnOpenSystemSettingsClick -> {
                missedMessagedRepository.openNotificationSettings()
            }
        }
    }

}