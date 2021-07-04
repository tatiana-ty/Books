package ru.geekbrains.books.data.network

import android.content.Context
import io.reactivex.Observable

class NetworkStateRepositoryImpl(private val context: Context) : NetworkStateRepository {

    override fun watchForNetworkState(): Observable<NetworkState> =
        NetworkStateObservable(context)
            .cacheWithInitialCapacity(1)
            .skip(1)

}