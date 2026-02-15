package zed.rainxch.februaryminichallenges.last_active.domain.repository

import kotlinx.coroutines.flow.Flow

interface LastActiveRepository {
    fun getLastActive() : Flow<Long?>
    fun setLastActive(lastActiveMillis: Long)
}