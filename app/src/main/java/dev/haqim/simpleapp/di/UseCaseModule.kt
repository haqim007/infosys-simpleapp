package dev.haqim.simpleapp.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.haqim.simpleapp.data.repository.AppRepository
import dev.haqim.simpleapp.domain.repository.IAppRepository
import dev.haqim.simpleapp.domain.usecase.GetUserUseCase
import dev.haqim.simpleapp.domain.usecase.IGetUserUseCase
import dev.haqim.simpleapp.domain.usecase.ILoginUseCase
import dev.haqim.simpleapp.domain.usecase.ILogoutUseCase
import dev.haqim.simpleapp.domain.usecase.LoginUseCase
import dev.haqim.simpleapp.domain.usecase.LogoutUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule{

    @Binds
    @Singleton
    abstract fun provideLoginUseCase(
        useCase: LoginUseCase
    ): ILoginUseCase

    @Binds
    @Singleton
    abstract fun provideGetUserUseCase(
        useCase: GetUserUseCase
    ): IGetUserUseCase

    @Binds
    @Singleton
    abstract fun provideLogoutUseCase(
        useCase: LogoutUseCase
    ): ILogoutUseCase
}