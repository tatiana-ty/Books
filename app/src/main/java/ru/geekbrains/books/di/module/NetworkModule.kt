package ru.geekbrains.books.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.geekbrains.books.R.string.books_api
import ru.geekbrains.books.data.api.BooksApi
import ru.geekbrains.books.data.api.BooksApiInterceptor
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideGitHubApi(context: Context): BooksApi =
        Retrofit.Builder()
            .baseUrl(context.getString(books_api))
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(BooksApiInterceptor)
                    .addInterceptor(HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    })
                    .build()
            )
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BooksApi::class.java)

}