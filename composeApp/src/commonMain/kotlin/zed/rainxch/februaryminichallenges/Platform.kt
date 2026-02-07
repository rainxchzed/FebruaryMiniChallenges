package zed.rainxch.februaryminichallenges

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform