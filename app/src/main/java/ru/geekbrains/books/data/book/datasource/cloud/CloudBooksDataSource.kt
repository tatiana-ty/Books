package ru.geekbrains.books.data.book.datasource.cloud

import io.reactivex.Observable
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

    override fun getBooks(): Observable<List<Book>> {
        val res = booksApi
            .getBooks()
            .delay(1L, SECONDS)
            .flattenAsObservable { books -> books.results.lists }
            //.map { list -> list.books }
            //.toList()
            .map { list -> list.books}

        return res
    }

}