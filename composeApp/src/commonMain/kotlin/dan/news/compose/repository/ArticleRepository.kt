package dan.news.compose.repository

import dan.news.compose.model.NewsResponse
import dan.news.compose.network.ResultState
import kotlinx.coroutines.flow.Flow

interface ArticleRepository {

    suspend fun getNews(): Flow<ResultState<NewsResponse>>

}