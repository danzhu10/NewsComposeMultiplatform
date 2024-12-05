package dan.news.compose.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import composenews.composeapp.generated.resources.Res
import composenews.composeapp.generated.resources.compose_multiplatform
import dan.news.compose.component.ErrorView
import dan.news.compose.component.SpacingH10
import dan.news.compose.component.TopAppBarTitleCenter
import dan.news.compose.route.NavigationItem
import dan.news.compose.theme.FontTypography
import dan.news.compose.vm.NewsViewModel
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import moe.tlaster.precompose.navigation.Navigator
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.koinInject

@Composable
fun NewsScreen(
    navigator: Navigator,
    viewModel: NewsViewModel = koinInject()
) {
    val newsListState = viewModel.newsHome.collectAsState().value
    LaunchedEffect(viewModel) {
        viewModel.getNewsHome()
    }

    Scaffold(topBar = {
        TopAppBarTitleCenter(NavigationItem.ListNews.title)
    }) {
        Box(Modifier.fillMaxSize().padding(it)) {
            if (newsListState.isLoading) {
                CircularProgressIndicator(
                    color = Color.Blue,
                    modifier = Modifier.align(Alignment.Center)
                )
            } else if (!newsListState.error.isNullOrEmpty()) {
                ErrorView("There is an Error") {
                    viewModel.getNewsHome()
                }
            } else {
                Column(Modifier.verticalScroll(rememberScrollState()).fillMaxSize()) {
                    newsListState.data?.articles?.forEach {
                        NewsItem(
                            onClickItem = {
                                viewModel.setDetailNews(it)
                                navigator.navigate(NavigationItem.DetailNews.route)
                            },
                            title = it.title ?: "",
                            image = it.urlToImage ?: "",
                            date = it.publishedAt ?: "",
                        )
                        SpacingH10()
                    }
                }
            }
        }
    }
}


@Composable
fun NewsItem(
    date: String = "",
    image: String = "",
    title: String = "",
    onClickItem: () -> Unit
) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(bottom = 12.dp)
        .clickable {
            onClickItem()
        }) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(modifier = Modifier.height(100.dp)) {
                KamelImage(
                    resource = asyncPainterResource(image.ifEmpty { painterResource(Res.drawable.compose_multiplatform) }),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .clip(RoundedCornerShape(3.dp))
                        .width(120.dp)
                        .fillMaxHeight()
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    text = title,
                    style = FontTypography.titleMedium.copy(
                        lineHeight = 24.sp,
                        fontWeight = FontWeight.W600
                    ),
                    maxLines = 3,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
                Row {
                    val modifier = Modifier
                        .padding(start = 0.dp)
                        .fillMaxWidth()
                    Text(
                        style = FontTypography.titleSmall.copy(fontWeight = FontWeight.W400),
                        text = date,
                        modifier = modifier,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
            }
        }
    }
}
