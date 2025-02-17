package dev.haqim.simpleapp.domain.usecase

import dev.haqim.simpleapp.domain.repository.IAppRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    private val repository: IAppRepository
) {
    operator fun invoke(): Flow<Boolean> = repository.logout()
}