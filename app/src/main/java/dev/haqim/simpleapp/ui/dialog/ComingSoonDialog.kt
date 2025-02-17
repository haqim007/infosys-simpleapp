package dev.haqim.simpleapp.ui.dialog

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ComingSoonDialog(onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(text = "Coming Soon!", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        },
        text = {
            Text("This feature is not available yet. Stay tuned for updates!", fontSize = 16.sp)
        },
        confirmButton = {
            Button(
                onClick = onDismiss,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF6F3C))
            ) {
                Text("OK", color = Color.White)
            }
        },
        shape = RoundedCornerShape(12.dp),
        containerColor = Color.White
    )
}