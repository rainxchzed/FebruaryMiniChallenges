package zed.rainxch.februaryminichallenges.missed_messages.domain

import kotlinx.coroutines.flow.Flow

interface MissedMessagedRepository {
    fun notificationEnabledFlow() : Flow<Boolean>
    fun openNotificationSettings()
}