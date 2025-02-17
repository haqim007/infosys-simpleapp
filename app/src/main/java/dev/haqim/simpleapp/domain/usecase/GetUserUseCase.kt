package dev.haqim.simpleapp.domain.usecase

import dev.haqim.simpleapp.domain.model.User
import dev.haqim.simpleapp.domain.repository.IAppRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val repository: IAppRepository
) {
    operator fun invoke(): Flow<User> = repository.getUser()
}