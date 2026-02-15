package zed.rainxch.februaryminichallenges

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.viewModelFactory
import zed.rainxch.februaryminichallenges.last_active.data.repository.LastActiveRepositoryImpl
import zed.rainxch.februaryminichallenges.last_active.presentation.LastActiveRoot
import zed.rainxch.februaryminichallenges.last_active.presentation.LastActiveViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            LastActiveRoot(
                viewModel = viewModel(factory = viewModelFactory {
                    addInitializer(LastActiveViewModel::class, initializer = {
                        LastActiveViewModel(
                            lastActiveRepository = LastActiveRepositoryImpl()
                        )
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