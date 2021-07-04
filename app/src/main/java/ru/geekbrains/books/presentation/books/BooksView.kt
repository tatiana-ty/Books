package ru.geekbrains.books.presentation.books

import moxy.MvpView
import moxy.viewstate.strategy.alias.SingleState
import ru.geekbrains.books.data.book.model.Book
import ru.geekbrains.books.presentation.abs.ErrorView
import ru.geekbrains.books.presentation.abs.LoadingView

interface BooksView : LoadingView, ErrorView, MvpView {

    @SingleState
    fun showBooks(books: List<Book>)

}