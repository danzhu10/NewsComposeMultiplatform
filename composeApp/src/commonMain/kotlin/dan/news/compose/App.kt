package dan.news.compose

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dan.news.compose.route.Navigation
import moe.tlaster.precompose.PreComposeApp
import moe.tlaster.precompose.navigation.rememberNavigator

@Composable
fun App() {
    PreComposeApp {
        val navigator = rememberNavigator()
        Scaffold { paddingValues ->
            BoxWithConstraints(
                modifier = Modifier.fillMaxSize()
            ) {
                Column(modifier = Modifier.fillMaxSize()) {
                    Navigation(
                        navigator = navigator,
                        paddingValues = paddingValues
                    )
                }
            }
        }
    }
}
