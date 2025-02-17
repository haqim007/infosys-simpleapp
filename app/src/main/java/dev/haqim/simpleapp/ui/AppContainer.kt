package dev.haqim.simpleapp.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import dev.haqim.simpleapp.domain.model.User
import dev.haqim.simpleapp.ui.login.LoginScreen
import dev.haqim.simpleapp.ui.nav.LoginRoute
import dev.haqim.simpleapp.ui.nav.PersonalInfoRoute
import dev.haqim.simpleapp.ui.nav.ProfileRoute
import dev.haqim.simpleapp.ui.nav.SplashRoute
import dev.haqim.simpleapp.ui.personalinfo.PersonalInfoArgsType
import dev.haqim.simpleapp.ui.personalinfo.PersonalInfoScreen
import dev.haqim.simpleapp.ui.personalinfo.PersonalInfoScreenArgs
import dev.haqim.simpleapp.ui.profile.ProfileScreen
import dev.haqim.simpleapp.ui.splash.SplashScreen
import dev.haqim.simpleapp.ui.theme.SimpleAppTheme
import kotlin.reflect.typeOf

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppContainer(
    viewModel: AppViewModel = hiltViewModel()
) {
    val navController = rememberNavController()
    val state = viewModel.state.collectAsState()

    LaunchedEffect(key1 = state.value.isSessionValid) {
        if(state.value.isSessionValid == true){
            navController.navigate(ProfileRoute){
                popUpTo(navController.graph.id){
                    inclusive = true
                }
            }
        }else if(state.value.isSessionValid == false){
            navController.navigate(LoginRoute){
                popUpTo(navController.graph.id){
                    inclusive = true
                }
            }
        }
    }

    SimpleAppTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
//            topBar = {
//                TopAppBar(
//                    title = { Text("My App") },
//                    navigationIcon = {
//                        if (navController.previousBackStackEntry != null) {
//                            IconButton(onClick = { navController.popBackStack() }) {
//                                Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
//                            }
//                        }
//                    }
//                )
//            }
        ) { innerPadding ->
            Surface(
                modifier = Modifier.padding(innerPadding),
                color = MaterialTheme.colorScheme.background,
            ) {
                NavHost(navController = navController, startDestination = SplashRoute) {
                    composable<SplashRoute> {
                        SplashScreen {
                            viewModel.doAction(AppUiAction.CheckSession)
                        }
                    }
                    composable<LoginRoute> {
                        LoginScreen()
                    }
                    composable<ProfileRoute> {
                        ProfileScreen(
                            navigateToPersonalInfo = { user ->
                                navController.navigate(PersonalInfoRoute(PersonalInfoScreenArgs(user)))
                            }
                        )
                    }
                    composable<PersonalInfoRoute>(
                        typeMap = mapOf(typeOf<PersonalInfoScreenArgs>() to PersonalInfoArgsType)
                    ) {backStackEntry ->
                        val param: PersonalInfoScreenArgs by remember {
                            mutableStateOf(backStackEntry.toRoute<PersonalInfoRoute>().args)
                        }
                        PersonalInfoScreen(user = param.user) {
                            navController.popBackStack()
                        }
                    }
                }
            }
        }

    }
}