package ru.geekbrains.books.presentation.book

import moxy.MvpView
import moxy.viewstate.strategy.alias.SingleState
import ru.geekbrains.books.data.book.model.Book
import ru.geekbrains.books.presentation.abs.ErrorView

interface BookView : ErrorView, MvpView {

    @SingleState
    fun showBook(book: Book)

}