package zed.rainxch.februaryminichallenges.core.presentation.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

object StillConnectedColors {
    val background = Color(0xffF4F9FF)
    val textPrimary = Color(0xff071121)
    val surface = Brush.linearGradient(
        listOf(
            Color(0xffFFD7E6),
            Color(0xffFFD7E6).copy(alpha = .2f),
        )
    )
}