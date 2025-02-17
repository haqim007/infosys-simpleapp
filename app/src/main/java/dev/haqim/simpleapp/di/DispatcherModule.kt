package dev.haqim.simpleapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DispatcherModule {

    @Provides
    @Singleton
    @DispatcherIO
    fun provideIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    @Singleton
    @DispatcherDefault
    fun provideDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default
}
