package ru.geekbrains.books.presentation.book

import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory

@AssistedFactory
interface BookPresenterFactory {

    fun create(
        @Assisted("bookTitle")bookTitle: String,
        @Assisted("bookAuthor")bookAuthor: String
    ): BookPresenter

}