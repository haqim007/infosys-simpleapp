package dev.haqim.simpleapp.ui.nav

import dev.haqim.simpleapp.ui.personalinfo.PersonalInfoScreenArgs
import kotlinx.serialization.Serializable

@Serializable
object SplashRoute

@Serializable
object LoginRoute

@Serializable
object ProfileRoute

@Serializable
data class PersonalInfoRoute(
    val args: PersonalInfoScreenArgs
)