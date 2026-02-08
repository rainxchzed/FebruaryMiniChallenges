package zed.rainxch.februaryminichallenges

import androidx.compose.ui.window.ComposeUIViewController
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.viewModelFactory
import zed.rainxch.februaryminichallenges.still_connected.data.service.IOSConnectivityManager
import zed.rainxch.februaryminichallenges.still_connected.presentation.StillConnectedViewModel

fun MainViewController() = ComposeUIViewController {
    App(viewModel = viewModel(factory = viewModelFactory {
        addInitializer(StillConnectedViewModel::class, initializer = {
            StillConnectedViewModel(IOSConnectivityManager())
        })
    }))
}
