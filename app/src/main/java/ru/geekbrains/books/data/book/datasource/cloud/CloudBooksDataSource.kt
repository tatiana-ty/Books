package ru.geekbrains.books.data.book.datasource.cloud

import io.reactivex.Single
import ru.geekbrains.books.data.api.BooksApi
import ru.geekbrains.books.data.book.datasource.BooksDataSource
import ru.geekbrains.books.data.book.model.Book
import java.util.concurrent.TimeUnit.SECONDS
import javax.inject.Inject

class CloudBooksDataSource
    @Inject constructor(
        private val booksApi: BooksApi
            ) : BooksDataSource {

    override fun getBooks(): Single<List<Book>> {
        val res = booksApi
            .getBooks()
            .delay(1L, SECONDS)
        val books = emptyList<Book>().toMutableList()
        for(i in res.blockingGet().results.lists) {
            books += i.books
        }
        return Single.just(books)
    }


}