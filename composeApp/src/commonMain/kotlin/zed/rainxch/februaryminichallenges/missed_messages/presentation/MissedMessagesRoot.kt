package zed.rainxch.februaryminichallenges.missed_messages.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun MissedMessagesRoot(
    viewModel: MissedMessagesViewModel = viewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    MissedMessagesScreen(
        state = state,
        onAction = viewModel::onAction
    )
}

@Composable
fun MissedMessagesScreen(
    state: MissedMessagesState,
    onAction: (MissedMessagesAction) -> Unit,
) {

}

@Preview
@Composable
private fun Preview() {
    MissedMessagesScreen(
        state = MissedMessagesState(),
        onAction = {}
    )
}