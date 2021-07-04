package ru.geekbrains.books.di.module

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.geekbrains.books.data.storage.BooksStorage
import ru.geekbrains.books.di.InMemory
import ru.geekbrains.books.di.Persisted
import javax.inject.Singleton

@Module
class StorageModule {

    @InMemory
    @Singleton
    @Provides
    fun provideInMemoryStorage(context: Context): BooksStorage =
        Room.inMemoryDatabaseBuilder(context, BooksStorage::class.java)
            .fallbackToDestructiveMigration()
            .build()

    @Persisted
    @Singleton
    @Provides
    fun provideDatabaseStorage(context: Context): BooksStorage =
        Room.databaseBuilder(context, BooksStorage::class.java, "books.db")
            .build()

}