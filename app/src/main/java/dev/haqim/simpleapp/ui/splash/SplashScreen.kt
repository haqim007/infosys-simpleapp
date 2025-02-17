package dev.haqim.simpleapp.ui.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Coffee
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.haqim.simpleapp.ui.theme.SimpleAppTheme
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navigate: () -> Unit
) {
    LaunchedEffect(key1 = Unit) {
        delay(3000)
        navigate()
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.wrapContentSize()
        ) {
            Icon(
                imageVector = Icons.Filled.Coffee,
                contentDescription = "Splash Icon",
                modifier = Modifier.size(100.dp),
                tint = Color.Black
            )
            Text(
                modifier = Modifier.size(100.dp),
                text = "Coffee",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    SimpleAppTheme {
        SplashScreen{}
    }
}
