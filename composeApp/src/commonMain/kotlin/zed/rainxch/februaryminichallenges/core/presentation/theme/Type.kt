package zed.rainxch.februaryminichallenges.core.presentation.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import februaryminichallenges.composeapp.generated.resources.Res
import februaryminichallenges.composeapp.generated.resources.stack_sans_medium
import org.jetbrains.compose.resources.Font

val stackSansFont: FontFamily
    @Composable
    get() = FontFamily(
        Font(Res.font.stack_sans_medium, FontWeight.Medium)
    )