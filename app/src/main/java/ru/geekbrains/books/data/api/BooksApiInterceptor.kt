package ru.geekbrains.books.data.api

import okhttp3.Interceptor
import okhttp3.Response

object BooksApiInterceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response =
        chain.proceed(
            chain.request()
                .newBuilder()
                .header("api-key", "9AHMq10FH8cD9tgYIGyuCTShZC6FEUI9")
                .build()
        )

}