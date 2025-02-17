package dev.haqim.simpleapp.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.haqim.simpleapp.data.repository.AppRepository
import dev.haqim.simpleapp.domain.repository.IAppRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule{

    @Binds
    @Singleton
    abstract fun provideAppRepository(
        repository: AppRepository
    ): IAppRepository
}