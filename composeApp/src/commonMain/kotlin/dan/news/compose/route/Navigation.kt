package dan.news.compose.route

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import dan.news.compose.screen.DetailNewsScreen
import dan.news.compose.screen.NewsScreen
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.Navigator

@Composable
fun Navigation(
    navigator: Navigator,
    paddingValues: PaddingValues = PaddingValues()
) {
    NavHost(navigator = navigator, initialRoute = NavigationItem.ListNews.route) {
        scene(NavigationItem.ListNews.route) {
            NewsScreen(navigator = navigator)
        }
        scene(NavigationItem.DetailNews.route) {
            DetailNewsScreen(navigator = navigator)
        }

    }
}
