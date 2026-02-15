package zed.rainxch.februaryminichallenges.last_active.presentation.util

import kotlinx.datetime.DatePeriod
import kotlinx.datetime.TimeZone
import kotlinx.datetime.minus
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Clock
import kotlin.time.Instant

fun formatLastActive(
    timestampMillis: Long,
    nowMillis: Long = Clock.System.now().toEpochMilliseconds(),
    timeZone: TimeZone = TimeZone.currentSystemDefault()
): String {

    val activityInstant = Instant.fromEpochMilliseconds(timestampMillis)
    val nowInstant = Instant.fromEpochMilliseconds(nowMillis)

    val activityDateTime = activityInstant.toLocalDateTime(timeZone)
    val nowDateTime = nowInstant.toLocalDateTime(timeZone)

    val activityDate = activityDateTime.date
    val nowDate = nowDateTime.date

    return when {
        activityDate == nowDate -> {
            "Last active at ${activityDateTime.hour.pad()}:${activityDateTime.minute.pad()}"
        }

        activityDate == nowDate.minus(DatePeriod(days = 1)) -> {
            "Last active yesterday"
        }

        else -> {
            val month = activityDate.month.name.lowercase()
                .replaceFirstChar { it.uppercase() }
                .take(3)

            "Last active on $month ${activityDate.day.pad()}"
        }
    }
}

private fun Int.pad() = this.toString().padStart(2, '0')