package dan.news.compose

import dan.news.compose.di.appModule
import dan.news.compose.di.platformModule
import org.koin.core.context.startKoin

fun initKoin() =
    startKoin {
        modules(appModule(), platformModule())
    }
