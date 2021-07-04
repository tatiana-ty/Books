package ru.geekbrains.books.data.book.datasource.cache

import io.reactivex.Single
import ru.geekbrains.books.data.book.datasource.BooksDataSource
import ru.geekbrains.books.data.book.model.Book

interface CacheBooksDataSource : BooksDataSource {

    fun retain(books: List<Book>): Single<List<Book>>

}