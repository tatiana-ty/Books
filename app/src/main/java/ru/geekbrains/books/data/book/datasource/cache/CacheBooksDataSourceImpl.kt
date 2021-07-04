package ru.geekbrains.books.data.book.datasource.cache

import io.reactivex.Single
import ru.geekbrains.books.data.book.model.Book
import ru.geekbrains.books.data.storage.BooksDao
import ru.geekbrains.books.data.storage.BooksStorage
import ru.geekbrains.books.di.Persisted
import javax.inject.Inject

class CacheBooksDataSourceImpl
@Inject constructor(
    @Persisted booksStorage: BooksStorage
) : CacheBooksDataSource {

    private val booksDao: BooksDao =
        booksStorage
            .booksDao()

    override fun getBooks(): Single<List<Book>> =
        booksDao
            .getBooks()

    override fun retain(books: List<Book>): Single<List<Book>> =
        booksDao
            .retain(books)
            .andThen(getBooks())

}