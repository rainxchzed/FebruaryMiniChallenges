package zed.rainxch.februaryminichallenges

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import zed.rainxch.februaryminichallenges.missed_messages.presentation.MissedMessagesRoot
import zed.rainxch.februaryminichallenges.missed_messages.presentation.MissedMessagesViewModel
import zed.rainxch.februaryminichallenges.still_connected.presentation.StillConnectedRoot
import zed.rainxch.februaryminichallenges.still_connected.presentation.StillConnectedViewModel

@Composable
@Preview
fun App(
    viewModel: MissedMessagesViewModel = viewModel()
) {
    MissedMessagesRoot(
        viewModel = viewModel
    )
}