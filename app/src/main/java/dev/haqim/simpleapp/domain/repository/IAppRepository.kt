package dev.haqim.simpleapp.domain.repository

import dev.haqim.simpleapp.data.mechanism.Resource
import dev.haqim.simpleapp.domain.model.User
import kotlinx.coroutines.flow.Flow

interface IAppRepository {
    fun hasLogin(): Flow<Boolean>
    fun login(username: String, password: String): Flow<Resource<User>>
    fun logout(): Flow<Boolean>
    fun getUser(): Flow<User>
}