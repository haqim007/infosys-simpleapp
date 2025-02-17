package dev.haqim.simpleapp.ui.login

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.haqim.simpleapp.data.mechanism.Resource
import dev.haqim.simpleapp.domain.model.User
import dev.haqim.simpleapp.domain.usecase.ILoginUseCase
import dev.haqim.simpleapp.ui.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: ILoginUseCase
): BaseViewModel<LoginState, LoginAction>() {
    override val _state = MutableStateFlow(LoginState())
    override fun doAction(action: LoginAction) {
        when (action) {
            is LoginAction.Login -> {
                login()
            }

            is LoginAction.SetEmail -> _state.update { state ->
                state.copy(email = action.email)
            }
            is LoginAction.SetPassword -> _state.update { state ->
                state.copy(password = action.password)
            }
        }
    }

    private fun validate(): Boolean{
        _state.update { state ->
            state.copy(
                email = state.email ?: "",
                password = state.password ?: ""
            )
        }

        return !state.value.email.isNullOrEmpty() && !state.value.password.isNullOrEmpty()
    }

    private fun login() {
        println(state.value.email)
        println(state.value.password)
        if (!validate()) return
        viewModelScope.launch {
            println(state.value.email)
            println(state.value.password)
            loginUseCase.process(state.value.email ?: "", state.value.password ?: "")
                .collect {
                    _state.update { state ->
                        state.copy(result = it)
                    }
                }
        }
    }
}

data class LoginState(
    val email: String? = null,
    val password: String? = null,
    val result: Resource<User> = Resource.Idle()
)

sealed class LoginAction {
    data object Login: LoginAction()
    data class SetEmail(val email: String): LoginAction()
    data class SetPassword(val password: String): LoginAction()
}