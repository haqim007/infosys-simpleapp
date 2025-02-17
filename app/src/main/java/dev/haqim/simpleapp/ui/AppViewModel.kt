package dev.haqim.simpleapp.ui

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.haqim.simpleapp.domain.usecase.CheckSessionUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(
    private val checkSessionUseCase: CheckSessionUseCase
) : BaseViewModel<AppState, AppUiAction>(){
    override val _state = MutableStateFlow(AppState())
    override fun doAction(action: AppUiAction) {
        when(action){
            is AppUiAction.CheckSession -> {
                viewModelScope.launch {
                   checkSessionUseCase().collect{
                       _state.update { state ->
                           state.copy(isSessionValid = it)
                       }
                   }
                }
            }
        }
    }
}

data class AppState(
    val isSessionValid: Boolean? = null
)

sealed class AppUiAction{
    data object CheckSession: AppUiAction()
}