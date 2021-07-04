package ru.geekbrains.books.data.network

import io.reactivex.Observable

interface NetworkStateRepository {

    fun watchForNetworkState(): Observable<NetworkState>

}