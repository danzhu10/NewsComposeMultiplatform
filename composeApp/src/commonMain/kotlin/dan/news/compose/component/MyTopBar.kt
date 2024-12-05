package dan.news.compose.component

import androidx.compose.foundation.background
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import dan.news.compose.theme.FontTypography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarNormal(
    title: String,
    onBackPressed: ()->Unit,
    icon: ImageVector = Icons.Default.ArrowBack
) {

    CenterAlignedTopAppBar(
        modifier = Modifier.background(MaterialTheme.colorScheme.background),
        navigationIcon = {
            IconButton(
                onClick = {
                    onBackPressed()
                },
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = ""
                )
            }
        }, title = {
            Text(
                title, style = FontTypography.titleMedium
            )
        })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarTitleCenter(
    title: String,
) {
    CenterAlignedTopAppBar(
        modifier = Modifier.background(MaterialTheme.colorScheme.background),
        title = {
            Text(text = title, style = FontTypography.titleMedium)
        },
    )
}
