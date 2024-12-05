package dan.news.compose.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.multiplatform.webview.web.WebView
import com.multiplatform.webview.web.rememberWebViewStateWithHTMLData
import dan.news.compose.component.SpacingH10
import dan.news.compose.component.SpacingH5
import dan.news.compose.component.TopBarNormal
import dan.news.compose.route.NavigationItem
import dan.news.compose.theme.FontTypography
import dan.news.compose.vm.NewsViewModel
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import moe.tlaster.precompose.flow.collectAsStateWithLifecycle
import moe.tlaster.precompose.navigation.Navigator
import org.koin.compose.koinInject

@Composable
fun DetailNewsScreen(
    navigator: Navigator,
    viewModel: NewsViewModel = koinInject()
) {
    val contentNews = viewModel.contentNews.collectAsStateWithLifecycle()

    Scaffold(topBar = {
        TopBarNormal(
            title = NavigationItem.DetailNews.title,
            onBackPressed = { navigator.goBack() })
    }) {
        val listState = rememberLazyListState()
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {

            LazyColumn(
                state = listState,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                item {
                    val data = contentNews.value.data!!
                    Column(Modifier.padding(10.dp)) {
                        SpacingH5()
                        Text(
                            style = FontTypography.titleLarge.copy(
                                lineHeight = 29.sp,
                                fontWeight = FontWeight.W700
                            ),
                            text = data.title ?: "",
                            modifier = Modifier.fillMaxWidth(),
                            maxLines = 4
                        )

                        SpacingH5()
                        Text(
                            style = FontTypography.labelMedium.copy(fontWeight = FontWeight.W400),
                            text = data.publishedAt ?: "",
                            modifier = Modifier.fillMaxWidth()
                        )
                        SpacingH10()
                        PostHeaderImage(data.urlToImage ?: "")
                        SpacingH10(15.dp)
                        ArticleContentWebView(data.content ?: "")
                    }
                }
            }
        }
    }
}


@Composable
fun ArticleContentWebView(
    content: String
) {
    val webViewState = rememberWebViewStateWithHTMLData(
        data = content
    )
    WebView(
        state = webViewState,
        modifier = Modifier.fillMaxSize()
    )
}

@Composable
fun PostHeaderImage(foto: String) {
    val imageModifier = Modifier
        .height(200.dp)
        .fillMaxWidth()
    Box {
        KamelImage(
            asyncPainterResource(foto),
            contentDescription = "",
            contentScale = ContentScale.Fit,
            modifier = imageModifier
        )

    }
    SpacingH5()
}
