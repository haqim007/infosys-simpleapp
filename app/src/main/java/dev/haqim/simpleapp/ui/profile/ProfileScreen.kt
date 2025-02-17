package dev.haqim.simpleapp.ui.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Help
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Payment
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import dev.haqim.simpleapp.domain.model.User
import dev.haqim.simpleapp.ui.dialog.ComingSoonDialog
import dev.haqim.simpleapp.ui.theme.SimpleAppTheme

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = hiltViewModel(),
    navigateToPersonalInfo: (User) -> Unit,
) {
    val state by viewModel.state.collectAsState()
    val action = { action: ProfileAction -> viewModel.doAction(action) }

    LaunchedEffect(Unit) {
        action(ProfileAction.LoadUser)
    }

    ProfileContent(state, action, navigateToPersonalInfo)
}

@Composable
private fun ProfileContent(
    state: ProfileState,
    action: (ProfileAction) -> Unit,
    navigateToPersonalInfo: (User) -> Unit
) {
    val scrollState = rememberScrollState()
    var showDialog by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Profile Image
        Box(
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
                .background(Color(0xFFD9A7A7))
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Name and Bio
        Text(state.user?.name ?: "", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        Text("I love fast food", fontSize = 14.sp, color = Color.Gray)

        Spacer(modifier = Modifier.height(16.dp))

        // Profile options list
        Column(modifier = Modifier.fillMaxWidth()) {
            ProfileOption(icon = Icons.Filled.Person, title = "Personal Info") {
                state.user?.let { navigateToPersonalInfo(it) }
            }
            ProfileOption(icon = Icons.Filled.LocationOn, title = "Addresses"){
                showDialog = true
            }

            Spacer(modifier = Modifier.height(16.dp))

            ProfileOption(icon = Icons.Filled.ShoppingCart, title = "Cart"){
                showDialog = true
            }
            ProfileOption(icon = Icons.Filled.Favorite, title = "Favourite"){
                showDialog = true
            }
            ProfileOption(icon = Icons.Filled.Notifications, title = "Notifications"){
                showDialog = true
            }
            ProfileOption(icon = Icons.Filled.Payment, title = "Payment Method"){
                showDialog = true
            }

            Spacer(modifier = Modifier.height(16.dp))

            ProfileOption(icon = Icons.Filled.Help, title = "FAQs"){
                showDialog = true
            }
            ProfileOption(icon = Icons.Filled.Star, title = "User Reviews"){
                showDialog = true
            }
            ProfileOption(icon = Icons.Filled.Settings, title = "Settings"){
                showDialog = true
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Logout button
            ProfileOption(
                icon = Icons.AutoMirrored.Filled.ExitToApp,
                title = "Log Out",
                iconTint = Color.Red,
                textColor = Color.Red
            ) {
                action(ProfileAction.Logout)
            }
        }

        if (showDialog) {
            ComingSoonDialog(onDismiss = { showDialog = false })
        }
    }
}

@Composable
fun ProfileOption(
    icon: ImageVector,
    title: String,
    iconTint: Color = Color.Black,
    textColor: Color = Color.Black,
    onClick: () -> Unit = {}
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 2.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors().copy(
            containerColor = Color(0xFFF5F5F4)
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(imageVector = icon, contentDescription = title, tint = iconTint, modifier = Modifier.size(24.dp))
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = title, fontSize = 16.sp, color = textColor, modifier = Modifier.weight(1f))
            Icon(imageVector = Icons.Filled.KeyboardArrowRight, contentDescription = "Arrow", tint = Color.Gray)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    SimpleAppTheme {
        ProfileContent(ProfileState(), {}, {})
    }
}
