package ru.geekbrains.books.presentation.abs

import moxy.MvpView
import moxy.viewstate.strategy.alias.SingleState

interface ErrorView : MvpView {

    /**
     * Показывает ошибку.
     * @param message сообщение об ошибке
     */
    @SingleState
    fun showError(message: String?)

}