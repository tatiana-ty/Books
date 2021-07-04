package ru.geekbrains.books.data.book.datasource

import io.reactivex.Single
import ru.geekbrains.books.data.book.model.Book

interface BooksDataSource {

    fun getBooks(): Single<List<Book>>

}