package zed.rainxch.februaryminichallenges.last_active.presentation

sealed interface LastActiveAction {
    data object OnAppOnBackground: LastActiveAction
}