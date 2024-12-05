package dan.news.compose.network

import dan.news.compose.model.NewsResponse
import io.ktor.client.call.body
import io.ktor.client.request.get
import org.koin.core.component.KoinComponent

class ApiService : KoinComponent {

    suspend fun getNews(): NewsResponse {
        return KtorService.client
            .get(BASEURL.API_HOST + "top-headlines?country=us")
            .body<NewsResponse>()
    }

}