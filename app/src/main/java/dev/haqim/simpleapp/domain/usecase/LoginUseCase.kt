package dev.haqim.simpleapp.domain.usecase

import dev.haqim.simpleapp.data.mechanism.Resource
import dev.haqim.simpleapp.domain.model.User
import dev.haqim.simpleapp.domain.repository.IAppRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: IAppRepository
): ILoginUseCase {
    override fun process(
        username: String,
        password: String
    ): Flow<Resource<User>> = repository.login(username, password)
}