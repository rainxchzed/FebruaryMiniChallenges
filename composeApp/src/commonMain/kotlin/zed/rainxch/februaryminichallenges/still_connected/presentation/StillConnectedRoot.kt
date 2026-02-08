package zed.rainxch.februaryminichallenges.still_connected.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import februaryminichallenges.composeapp.generated.resources.Res
import februaryminichallenges.composeapp.generated.resources.airplane_mode
import februaryminichallenges.composeapp.generated.resources.connected
import februaryminichallenges.composeapp.generated.resources.disconnected
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.vectorResource
import zed.rainxch.februaryminichallenges.core.presentation.theme.stackSansFont
import zed.rainxch.februaryminichallenges.core.presentation.theme.StillConnectedColors
import zed.rainxch.februaryminichallenges.still_connected.domain.model.ConnectivityState

@Composable
fun StillConnectedRoot(
    viewModel: StillConnectedViewModel
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    StillConnectedScreen(
        state = state,
    )
}

@Composable
fun StillConnectedScreen(
    state: StillConnectedState,
) {
    Box(
        Modifier
            .fillMaxSize()
            .background(StillConnectedColors.background),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp, Alignment.CenterVertically)
        ) {
            val image = when (state.connectivityState) {
                ConnectivityState.Airplane -> Res.drawable.airplane_mode
                ConnectivityState.Connected -> Res.drawable.connected
                ConnectivityState.Disconnected -> Res.drawable.disconnected
            }
            val text = when (state.connectivityState) {
                ConnectivityState.Airplane -> "You Turned on Airplane Mode"
                ConnectivityState.Connected -> "You’re Connected"
                ConnectivityState.Disconnected -> "We Lost the Connection"
            }

            Box(
                modifier = Modifier
                    .width(300.dp)
                    .height(240.dp),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(240.dp)
                        .clip(CircleShape)
                        .background(StillConnectedColors.surface)
                )

                Image(
                    painter = painterResource(image),
                    contentDescription = null,
                    modifier = Modifier
                        .width(300.dp)
                        .height(240.dp),
                    contentScale = ContentScale.Crop
                )
            }

            Text(
                text = text,
                fontSize = 26.sp,
                fontWeight = FontWeight.Medium,
                color = StillConnectedColors.textPrimary,
                fontFamily = stackSansFont
            )
        }
    }
}

@Preview
@Composable
private fun Preview() {
    StillConnectedScreen(
        state = StillConnectedState()
    )
}