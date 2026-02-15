package zed.rainxch.februaryminichallenges.last_active.data.repository

import kotlinx.coroutines.flow.Flow
import zed.rainxch.februaryminichallenges.last_active.domain.repository.LastActiveRepository

class LastActiveRepositoryImpl : LastActiveRepository {
    override fun getLastActive(): Flow<Long?> {
        TODO("Not yet implemented")
    }

    override fun setLastActive(lastActiveMillis: Long) {
        TODO("Not yet implemented")
    }
}