package dev.haqim.simpleapp.ui.personalinfo

import android.net.Uri
import android.os.Bundle
import android.os.Parcelable
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.navigation.NavType
import dev.haqim.simpleapp.domain.model.User
import dev.haqim.simpleapp.domain.model.dummyUser
import dev.haqim.simpleapp.ui.dialog.ComingSoonDialog
import dev.haqim.simpleapp.ui.theme.SimpleAppTheme
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Composable
fun PersonalInfoScreen(user: User, onBackClick: () -> Unit) {
    var showDialog by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        // Top Bar
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back"
                )
            }
            Text(
                text = "Personal Info",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = "EDIT",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFFF6F3C),
                modifier = Modifier.clickable { showDialog = true }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Profile Image
        Box(
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
                .background(Color(0xFFD9A7A7))
                .align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Name and Bio
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
            Text(user.name, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Text("I love fast food", fontSize = 14.sp, color = Color.Gray)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Personal Info Card
        Card(
            modifier = Modifier.fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(12.dp),
            elevation = CardDefaults.cardElevation(2.dp),
            colors = CardDefaults.cardColors().copy(
                containerColor = Color(0xFFF5F5FA)
            )
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                PersonalInfoItem(Icons.Default.Person, "FULL NAME", user.name)
                HorizontalDivider(thickness = 0.5.dp, color = Color.LightGray)
                PersonalInfoItem(Icons.Default.Email, "EMAIL", user.email)
                HorizontalDivider(thickness = 0.5.dp, color = Color.LightGray)
                PersonalInfoItem(Icons.Default.Phone, "PHONE NUMBER", user.phone)
            }
        }

        if (showDialog) {
            ComingSoonDialog(onDismiss = { showDialog = false })
        }
    }
}

@Composable
fun PersonalInfoItem(icon: ImageVector, label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(imageVector = icon, contentDescription = label, tint = Color(0xFFFF6F3C), modifier = Modifier.size(24.dp))
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(text = label, fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color.Gray)
            Text(text = value, fontSize = 16.sp, fontWeight = FontWeight.Medium)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PersonalInfoScreenPreview() {
    SimpleAppTheme {
        PersonalInfoScreen(user = dummyUser, onBackClick = {})
    }
}

@Serializable
@Parcelize
data class PersonalInfoScreenArgs(
    val user: User,
): Parcelable

val PersonalInfoArgsType = object : NavType<PersonalInfoScreenArgs>(
    isNullableAllowed = false
) {
    override fun put(bundle: Bundle, key: String, value: PersonalInfoScreenArgs) {
        bundle.putParcelable(key, value)
    }
    override fun get(bundle: Bundle, key: String): PersonalInfoScreenArgs {
        return bundle.getParcelable<PersonalInfoScreenArgs>(key) as PersonalInfoScreenArgs
    }

    override fun serializeAsValue(value: PersonalInfoScreenArgs): String {
        // Serialized values must always be Uri encoded
        return Uri.encode(Json.encodeToString(value))
    }

    override fun parseValue(value: String): PersonalInfoScreenArgs {
        // Navigation takes care of decoding the string
        // before passing it to parseValue()
        return Json.decodeFromString<PersonalInfoScreenArgs>(value)
    }
}
