package dan.news.compose.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dan.news.compose.theme.TempoRed

@Composable
fun ErrorView(errorMessage: String, onBtnClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            errorMessage, style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center, fontSize = 16.sp
        )
        SpacingH10(6.dp)
        Button(
            colors = ButtonDefaults.buttonColors(containerColor = TempoRed),
            onClick = onBtnClick
        ) {
            Text("Try Again", color = Color.White)
        }
    }
}

@Composable
fun SpacingH10(value: Dp = 10.dp) {
    Spacer(modifier = Modifier.height(value))
}

@Composable
fun SpacingH5(value: Dp = 5.dp) {
    Spacer(modifier = Modifier.height(value))
}
