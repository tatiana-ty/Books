package ru.geekbrains.books.data.book.datasource.cache

import io.reactivex.Observable
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

    override fun getBooks(): Observable<List<Book>> =
        booksDao
            .getBooks().toObservable()

    override fun getBook(title: String, author: String): Single<Book> =
        booksDao
            .getBook(title, author)

    override fun retain(books: List<Book>): Observable<List<Book>> =
        booksDao
            .retain(books)
            .andThen(getBooks())

}