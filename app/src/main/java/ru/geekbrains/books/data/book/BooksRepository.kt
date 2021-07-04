package ru.geekbrains.books.data.book

import io.reactivex.Single
import ru.geekbrains.books.data.book.model.Book

interface BooksRepository {

    fun getBooks(): Single<List<Book>>
}