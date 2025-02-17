package dev.haqim.simpleapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.haqim.simpleapp.data.remote.config.ApiConfig
import dev.haqim.simpleapp.data.remote.config.AppService
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule{

    @Provides
    @Singleton
    fun provideMovieService(): AppService {
        return ApiConfig.createService(AppService::class.java)
    }
}