package ru.geekbrains.books.di.module

import dagger.Binds
import dagger.Module
import ru.geekbrains.books.data.book.BooksRepository
import ru.geekbrains.books.data.book.BooksRepositoryImpl
import ru.geekbrains.books.data.book.datasource.BooksDataSource
import ru.geekbrains.books.data.book.datasource.cache.CacheBooksDataSource
import ru.geekbrains.books.data.book.datasource.cache.CacheBooksDataSourceImpl
import ru.geekbrains.books.data.book.datasource.cloud.CloudBooksDataSource
import ru.geekbrains.books.di.Cache
import ru.geekbrains.books.di.Cloud
import javax.inject.Singleton

@Module(includes = [BookUiModule::class, StorageModule::class, NetworkModule::class])
interface BookModule {

    @Singleton
    @Binds
    fun bindBooksRepository(booksRepository: BooksRepositoryImpl): BooksRepository

    @Cloud
    @Singleton
    @Binds
    fun bindCloudDataSource(cloudDataSource: CloudBooksDataSource): BooksDataSource

    @Cache
    @Singleton
    @Binds
    fun bindCacheDataSource(cacheDataSource: CacheBooksDataSourceImpl): CacheBooksDataSource

}