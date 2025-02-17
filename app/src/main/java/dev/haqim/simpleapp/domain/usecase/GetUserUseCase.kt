package dev.haqim.simpleapp.domain.usecase

import dev.haqim.simpleapp.domain.model.User
import dev.haqim.simpleapp.domain.repository.IAppRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val repository: IAppRepository
) : IGetUserUseCase{
    override fun process(): Flow<User> = repository.getUser()
}