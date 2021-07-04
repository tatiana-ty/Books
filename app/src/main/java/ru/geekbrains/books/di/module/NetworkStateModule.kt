package ru.geekbrains.books.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.geekbrains.books.data.network.NetworkStateRepository
import ru.geekbrains.books.data.network.NetworkStateRepositoryImpl

@Module
class NetworkStateModule {

    @Provides
    fun provideNetworkStateRepository(context: Context): NetworkStateRepository =
        NetworkStateRepositoryImpl(context)

}