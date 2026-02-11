package zed.rainxch.februaryminichallenges

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.viewModelFactory
import zed.rainxch.februaryminichallenges.missed_messages.data.AndroidMissedMessagedRepository
import zed.rainxch.februaryminichallenges.missed_messages.presentation.MissedMessagesViewModel
import zed.rainxch.februaryminichallenges.still_connected.data.service.AndroidConnectivityManager
import zed.rainxch.februaryminichallenges.still_connected.presentation.StillConnectedViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            App(
                viewModel = viewModel(factory = viewModelFactory {
                    addInitializer(MissedMessagesViewModel::class, initializer = {
                        MissedMessagesViewModel(AndroidMissedMessagedRepository())
                    })
                })
            )
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}