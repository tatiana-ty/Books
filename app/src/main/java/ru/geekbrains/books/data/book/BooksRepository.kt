package ru.geekbrains.books.data.book

import io.reactivex.Observable
import io.reactivex.Single
import ru.geekbrains.books.data.book.model.Book

interface BooksRepository {

    fun getBooks(): Observable<List<Book>>

    fun getBook(title: String, author: String): Single<Book>
}