package ru.geekbrains.books.data.book

import io.reactivex.Observable
import io.reactivex.Single
import ru.geekbrains.books.data.book.datasource.BooksDataSource
import ru.geekbrains.books.data.book.datasource.cache.CacheBooksDataSource
import ru.geekbrains.books.data.book.model.Book
import ru.geekbrains.books.di.Cache
import ru.geekbrains.books.di.Cloud
import java.lang.Exception
import javax.inject.Inject

class BooksRepositoryImpl
@Inject constructor(
    @Cloud private val cloudBooksDataSource: BooksDataSource,
    @Cache private val cacheBooksDataSource: CacheBooksDataSource,
) : BooksRepository{

    override fun getBooks(): Observable<List<Book>> =
//        try {
            cloudBooksDataSource
                .getBooks()
                .flatMap(cacheBooksDataSource::retain)
//        } catch (e: Exception) {
//            println("here")
//
//            cacheBooksDataSource
//                .getBooks()
//        }

    override fun getBook(title: String, author: String): Single<Book> =
        cacheBooksDataSource
            .getBook(title, author)
}