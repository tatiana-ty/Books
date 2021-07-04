package ru.geekbrains.books.presentation.abs

import moxy.MvpView
import moxy.viewstate.strategy.alias.SingleState

interface LoadingView : MvpView {

    /**
     * Показывает процесс загрузки.
     */
    @SingleState
    fun showLoading()

}