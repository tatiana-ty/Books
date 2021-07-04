package ru.geekbrains.books.presentation.book

import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory

@AssistedFactory
interface BookPresenterFactory {

    fun create(@Assisted("book")book: String): BookPresenter

}