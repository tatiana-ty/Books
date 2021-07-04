package ru.geekbrains.books.data.book

import io.reactivex.Single
import ru.geekbrains.books.data.book.datasource.BooksDataSource
import ru.geekbrains.books.data.book.datasource.cache.CacheBooksDataSource
import ru.geekbrains.books.data.book.model.Book
import ru.geekbrains.books.di.Cache
import ru.geekbrains.books.di.Cloud
import javax.inject.Inject

class BooksRepositoryImpl
@Inject constructor(
    @Cloud private val cloudBooksDataSource: BooksDataSource,
    @Cache private val cacheBooksDataSource: CacheBooksDataSource,
) : BooksRepository{

    override fun getBooks(): Single<List<Book>> =
        cacheBooksDataSource
            .getBooks()
            .flatMap(::getFromCloudIfRequired)

    private fun getFromCloudIfRequired(books: List<Book>): Single<List<Book>> =
        if (books.isEmpty()) {
            cloudBooksDataSource
                .getBooks()
                .flatMap(cacheBooksDataSource::retain)
        } else {
            Single.just(books)
        }
}