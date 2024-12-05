package dan.news.compose.di

import dan.news.compose.network.ApiService
import dan.news.compose.repository.ArticleRepository
import dan.news.compose.usecase.NewsRepositoryImpl
import dan.news.compose.vm.NewsViewModel
import org.koin.core.module.Module
import org.koin.dsl.module

fun appModule() = module {
    single<ArticleRepository> { NewsRepositoryImpl(get()) }
    single { NewsViewModel(get()) }
    single { ApiService() }
}

expect fun platformModule(): Module
