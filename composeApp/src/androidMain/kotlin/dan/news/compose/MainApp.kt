package dan.news.compose

import android.app.Application
import dan.news.compose.di.appModule
import dan.news.compose.di.platformModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MainApp)
            androidLogger()
            modules(appModule(), platformModule())
        }
    }
}