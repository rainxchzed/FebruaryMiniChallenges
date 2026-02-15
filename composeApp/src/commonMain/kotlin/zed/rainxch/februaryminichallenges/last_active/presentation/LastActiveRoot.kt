package zed.rainxch.februaryminichallenges.last_active.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import februaryminichallenges.composeapp.generated.resources.Res
import februaryminichallenges.composeapp.generated.resources.last_active_profile
import org.jetbrains.compose.resources.painterResource
import zed.rainxch.februaryminichallenges.core.presentation.theme.LastActiveColors
import zed.rainxch.februaryminichallenges.core.presentation.theme.MissedMessagesColors
import zed.rainxch.februaryminichallenges.core.presentation.theme.interFontFamily
import zed.rainxch.februaryminichallenges.last_active.presentation.util.formatLastActive
import kotlin.time.Clock
import kotlin.time.Instant

@Composable
fun LastActiveRoot(
    viewModel: LastActiveViewModel = viewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val owner = LocalLifecycleOwner.current

    DisposableEffect(Unit) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_STOP) {
                viewModel.onAction(LastActiveAction.OnAppOnBackground)
            }
        }

        owner.lifecycle.addObserver(observer)

        onDispose {
            owner.lifecycle.removeObserver(observer)
        }
    }

    LastActiveScreen(
        state = state,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LastActiveScreen(
    state: LastActiveState,
) {
    Scaffold (
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Activity status",
                        fontFamily = interFontFamily,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp,
                        color = LastActiveColors.textPrimary
                    )
                },
                navigationIcon = {
                    OutlinedIconButton(
                        onClick = { },
                        colors = IconButtonDefaults.iconButtonColors(
                            containerColor = Color.Transparent,
                            contentColor = LastActiveColors.textSecondary
                        ),
                        modifier = Modifier.size(44.dp),
                        shape = CircleShape,
                        border = BorderStroke(
                            width = 1.dp,
                            color = LastActiveColors.outline
                        )
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                            contentDescription = null
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = LastActiveColors.background
                )
            )
        },
        containerColor = LastActiveColors.background
    ) { innerPadding ->
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(top = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(Res.drawable.last_active_profile),
                contentDescription = null,
                modifier = Modifier
                    .size(140.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )

            Spacer(Modifier.height(20.dp))

            Text(
                text = "Alice Cooper",
                textAlign = TextAlign.Center,
                fontSize = 32.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = interFontFamily,
                color = LastActiveColors.textPrimary
            )

            Spacer(Modifier.height(4.dp))

            Text(
                text = if(state.lastActiveMillis == null) {
                    "No activity yet"
                } else formatLastActive(state.lastActiveMillis),
                fontFamily = interFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                color = LastActiveColors.textSecondary
            )
        }
    }
}

@Preview
@Composable
private fun Preview() {
    LastActiveScreen(
        state = LastActiveState(
            lastActiveMillis = Clock.System.now().toEpochMilliseconds()
        ),
    )
}