package dev.haqim.simpleapp.data.remote.config

import dev.haqim.simpleapp.data.remote.response.UserResponse
import retrofit2.http.GET

interface AppService {
    @GET("users/1")
    suspend fun getUser(
    ): UserResponse
}