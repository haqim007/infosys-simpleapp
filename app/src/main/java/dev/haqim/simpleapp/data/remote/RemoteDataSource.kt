package dev.haqim.simpleapp.data.remote

import dev.haqim.simpleapp.data.remote.config.AppService
import dev.haqim.simpleapp.data.remote.response.UserResponse
import dev.haqim.simpleapp.data.remote.util.getResult
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(
    private val service: AppService
) {
    suspend fun getUser(): Result<UserResponse>{
        return getResult {
            service.getUser()
        }
    }
}