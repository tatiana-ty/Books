package ru.geekbrains.books.data.book.datasource

import io.reactivex.Observable
import io.reactivex.Single
import ru.geekbrains.books.data.book.model.Book

interface BooksDataSource {

    fun getBooks(): Observable<List<Book>>

}