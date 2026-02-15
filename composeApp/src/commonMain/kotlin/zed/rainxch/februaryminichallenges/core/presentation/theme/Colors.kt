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

object MissedMessagesColors {
    val background = Color(0xffEBF0F5)
    val surface = Color(0xffFFFFFF)
    val textPrimary = Color(0xff071121)
    val textSecondary = Color(0xff47566E)
    val button = Color(0xff5978E9)
    val successBg = Color(0xffEAF8F5)
    val successText = Color(0xff088767)
    val errorBg = Color(0xffFDF1F4)
    val errorText = Color(0xffC0083C)
}

object LastActiveColors {
    val background = Color(0xffF4F9FF)
    val textPrimary = Color(0xff071121)
    val textSecondary = Color(0xff47566E)
    val outline = Color(0xffD7E0ED)
}