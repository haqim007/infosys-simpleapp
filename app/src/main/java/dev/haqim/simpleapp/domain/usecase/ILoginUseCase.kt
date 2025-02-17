package dev.haqim.simpleapp.domain.usecase

import dev.haqim.simpleapp.data.mechanism.Resource
import dev.haqim.simpleapp.domain.model.User
import kotlinx.coroutines.flow.Flow

interface ILoginUseCase {
    fun process(username: String, password: String): Flow<Resource<User>>
}
