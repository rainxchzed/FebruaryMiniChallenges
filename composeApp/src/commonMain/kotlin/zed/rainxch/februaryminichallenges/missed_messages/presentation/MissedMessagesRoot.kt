package zed.rainxch.februaryminichallenges.missed_messages.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.NotificationsOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import zed.rainxch.februaryminichallenges.core.presentation.theme.MissedMessagesColors
import zed.rainxch.februaryminichallenges.core.presentation.theme.interFontFamily

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MissedMessagesScreen(
    state: MissedMessagesState,
    onAction: (MissedMessagesAction) -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(
                        onClick = {

                        },
                        colors = IconButtonDefaults.iconButtonColors(
                            containerColor = MissedMessagesColors.surface,
                            contentColor = MissedMessagesColors.textSecondary
                        ),
                        modifier = Modifier.size(36.dp)
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                            contentDescription = null
                        )
                    }
                },
                title = { },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MissedMessagesColors.background
                ),
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        },
        containerColor = MissedMessagesColors.background
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding)
                .padding(top = 24.dp)
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = "Notifications",
                fontFamily = interFontFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 28.sp,
                color = MissedMessagesColors.textPrimary
            )

            Spacer(Modifier.height(20.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MissedMessagesColors.surface
                ),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = 24.dp,
                            vertical = 20.dp
                        )
                        .padding(top = 12.dp),
                    verticalArrangement = Arrangement.spacedBy(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = if (state.isNotificationEnabled) {
                            Icons.Outlined.Notifications
                        } else Icons.Outlined.NotificationsOff,
                        contentDescription = null,
                        tint = if (state.isNotificationEnabled) {
                            MissedMessagesColors.successText
                        } else MissedMessagesColors.errorText,
                        modifier = Modifier
                            .size(56.dp)
                            .clip(CircleShape)
                            .background(
                                color = if (state.isNotificationEnabled) {
                                    MissedMessagesColors.successBg
                                } else MissedMessagesColors.errorBg
                            )
                            .padding(16.dp)
                    )

                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(4.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = if (state.isNotificationEnabled) {
                                "You will receive notifications"
                            } else "Notifications are turned off",
                            fontFamily = interFontFamily,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 22.sp,
                            color = MissedMessagesColors.textPrimary,
                            textAlign = TextAlign.Center
                        )

                        Text(
                            text = if (state.isNotificationEnabled) {
                                "Notifications are enabled for this app."
                            } else "You won’t see pop-up notifications when the app is in the background.",
                            fontFamily = interFontFamily,
                            fontWeight = FontWeight.Normal,
                            fontSize = 16.sp,
                            color = MissedMessagesColors.textSecondary,
                            textAlign = TextAlign.Center
                        )
                    }

                    OutlinedButton(
                        onClick = {
                            onAction(MissedMessagesAction.OnOpenSystemSettingsClick)
                        },
                        colors = ButtonDefaults.outlinedButtonColors(
                            containerColor = if(state.isNotificationEnabled) {
                                Color.Transparent
                            } else MissedMessagesColors.button
                        ),
                        border = BorderStroke(
                            width = 1.dp,
                            color = if(state.isNotificationEnabled) {
                                MissedMessagesColors.background
                            } else MissedMessagesColors.button
                        ),
                        modifier = Modifier.fillMaxWidth(),
                        contentPadding = PaddingValues(
                            vertical = 12.dp
                        )
                    ) {
                        Text(
                            text = "Open System Settings",
                            fontFamily = interFontFamily,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 18.sp,
                            color = if(state.isNotificationEnabled) {
                                MissedMessagesColors.textSecondary
                            } else MissedMessagesColors.surface
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    MissedMessagesScreen(
        state = MissedMessagesState(
            isNotificationEnabled = true
        ),
        onAction = {}
    )
}

@Preview
@Composable
private fun PreviewDisabledState() {
    MissedMessagesScreen(
        state = MissedMessagesState(
            isNotificationEnabled = false
        ),
        onAction = {}
    )
}