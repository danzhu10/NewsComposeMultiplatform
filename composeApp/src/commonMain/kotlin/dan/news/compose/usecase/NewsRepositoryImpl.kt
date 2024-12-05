package dan.news.compose.usecase

import dan.news.compose.model.NewsResponse
import dan.news.compose.network.ApiService
import dan.news.compose.network.ResultState
import dan.news.compose.network.safeApiCall
import dan.news.compose.repository.ArticleRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class NewsRepositoryImpl(
    private val apiService: ApiService
) : ArticleRepository {

    override suspend fun getNews(): Flow<ResultState<NewsResponse>> {
        return flowOf(
            safeApiCall {
                apiService.getNews()
            }
        )
    }

}