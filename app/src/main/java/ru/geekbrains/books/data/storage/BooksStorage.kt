package ru.geekbrains.books.data.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.geekbrains.books.data.book.model.Book

@Database(exportSchema = false, entities = [Book::class], version = 3)
abstract class BooksStorage : RoomDatabase() {

    abstract fun booksDao(): BooksDao

}