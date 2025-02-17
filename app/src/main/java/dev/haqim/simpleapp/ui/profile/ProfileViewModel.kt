package dev.haqim.simpleapp.ui.profile

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.haqim.simpleapp.domain.model.User
import dev.haqim.simpleapp.domain.usecase.GetUserUseCase
import dev.haqim.simpleapp.domain.usecase.LogoutUseCase
import dev.haqim.simpleapp.ui.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private val logoutUseCase: LogoutUseCase
) : BaseViewModel<ProfileState, ProfileAction>() {
    override val _state: MutableStateFlow<ProfileState> = MutableStateFlow(ProfileState())
    override fun doAction(action: ProfileAction) {
        when (action) {
            is ProfileAction.LoadUser -> loadUser()
            is ProfileAction.Logout -> logout()
        }
    }

    private fun loadUser() {
        viewModelScope.launch {
            getUserUseCase().collect {
                _state.update { state ->
                    state.copy(user = it)
                }
            }
        }
    }

    private fun logout() {
        viewModelScope.launch {
            logoutUseCase().first()
        }
    }

}

data class ProfileState(
    val user: User? = null
)

sealed class ProfileAction {
    data object LoadUser : ProfileAction()
    data object Logout : ProfileAction()
}