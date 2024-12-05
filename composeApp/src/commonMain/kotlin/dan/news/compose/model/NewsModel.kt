package dan.news.compose.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArticleModel(
    val source: Map<String?, String?>? = mapOf(),
    val author: String? = "",
    var title: String? = null,
    val description: String? = "",
    val urlToImage: String? = null,
    val url: String? = "",
    val publishedAt: String? = "",
    val content: String? = "",
)

@Serializable
data class NewsResponse(
    @SerialName("status")
    val status: String,
    @SerialName("totalResults")
    val totalResults: Int,
    @SerialName("articles")
    val articles: List<ArticleModel>
)
