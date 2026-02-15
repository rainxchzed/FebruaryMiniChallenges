package zed.rainxch.februaryminichallenges

import androidx.compose.ui.window.ComposeUIViewController
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.viewModelFactory
import zed.rainxch.februaryminichallenges.last_active.data.local.data_store.createDataStore
import zed.rainxch.februaryminichallenges.last_active.data.repository.LastActiveRepositoryImpl
import zed.rainxch.februaryminichallenges.last_active.presentation.LastActiveViewModel

fun MainViewController() = ComposeUIViewController {
    App(viewModel = viewModel(factory = viewModelFactory {
        addInitializer(LastActiveViewModel::class, initializer = {
            LastActiveViewModel(LastActiveRepositoryImpl(createDataStore()))
        })
    }))
}
