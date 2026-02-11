package zed.rainxch.februaryminichallenges.missed_messages.presentation

sealed interface MissedMessagesAction {
    data object OnOpenSystemSettingsClick : MissedMessagesAction
}