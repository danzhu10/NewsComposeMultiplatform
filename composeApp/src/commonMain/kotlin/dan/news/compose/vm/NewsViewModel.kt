package dan.news.compose.vm

import dan.news.compose.model.ArticleModel
import dan.news.compose.model.NewsResponse
import dan.news.compose.network.isLoading
import dan.news.compose.network.onFailure
import dan.news.compose.network.onSuccess
import dan.news.compose.repository.ArticleRepository
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class NewsUiState<T>(
    val isLoading: Boolean = true,
    val error: String? = null,
    val isError: Boolean = false,
    val data: T? = null
)

fun <T> createExceptionHandler(
    stateFlow: MutableStateFlow<NewsUiState<T>>
): CoroutineExceptionHandler {
    return CoroutineExceptionHandler { _, exception ->
        stateFlow.update { it.copy(isLoading = false, error = exception.message) }
    }
}

class NewsViewModel(val repo: ArticleRepository) : ViewModel() {

    val contentNews =
        MutableStateFlow(NewsUiState<ArticleModel>())

    fun setDetailNews(content: ArticleModel?) {
        contentNews.update { NewsUiState(data = content) }
    }

    private val _newsHome =
        MutableStateFlow(NewsUiState<NewsResponse>(isLoading = true))
    val newsHome = _newsHome.asStateFlow()

    private val coroutineExceptionHandlers = createExceptionHandler(_newsHome)

    fun getNewsHome() {
        viewModelScope.launch(coroutineExceptionHandlers) {
            repo.getNews().collectLatest { result ->
                result.isLoading { isLoading ->
                    _newsHome.update { it.copy(isLoading = isLoading) }
                }.onSuccess { response ->
                    _newsHome.update { it.copy(data = response) }
                }.onFailure { error ->
                    _newsHome.update { it.copy(error = error.message) }
                }
            }
        }
    }

}