package zed.rainxch.februaryminichallenges

import android.app.Application
import zed.rainxch.februaryminichallenges.utils.ApplicationContextHolder

class Application : Application() {

    override fun onCreate() {
        super.onCreate()

        ApplicationContextHolder.init(this)
    }
}