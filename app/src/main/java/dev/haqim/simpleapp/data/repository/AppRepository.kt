package dev.haqim.simpleapp.data.repository

import dev.haqim.simpleapp.data.mechanism.NetworkBoundResource
import dev.haqim.simpleapp.data.mechanism.Resource
import dev.haqim.simpleapp.data.mechanism.toUser
import dev.haqim.simpleapp.data.preference.AuthPreference
import dev.haqim.simpleapp.data.remote.RemoteDataSource
import dev.haqim.simpleapp.data.remote.response.UserResponse
import dev.haqim.simpleapp.di.DispatcherIO
import dev.haqim.simpleapp.domain.model.User
import dev.haqim.simpleapp.domain.repository.IAppRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AppRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val preferences: AuthPreference,
    @DispatcherIO
    private val dispatcherIO: CoroutineDispatcher
): IAppRepository {
    override fun hasLogin(): Flow<Boolean> {
        return preferences.hasLogin()
    }

    override fun login(username: String, password: String): Flow<Resource<User>> {
        return object : NetworkBoundResource<User, UserResponse>() {
            override suspend fun requestFromRemote(): Result<UserResponse> {
                return remoteDataSource.getUser()
            }

            override fun loadResult(responseData: UserResponse): Flow<User> {
                return flowOf(responseData.toUser())
            }

            override suspend fun onSuccess(responseData: UserResponse) {
                withContext(dispatcherIO){
                    preferences.saveUserToDataStore(responseData.toUser())
                }
            }

        }.asFlow()
    }

    override fun logout(): Flow<Boolean> {
        return flow {
            withContext(dispatcherIO) { preferences.logout() }
            emit(true)
        }
    }

    override fun getUser(): Flow<User> {
        return preferences.getUser()
    }
}