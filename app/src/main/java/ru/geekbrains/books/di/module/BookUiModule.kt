package ru.geekbrains.books.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ru.geekbrains.books.presentation.book.BookFragment
import ru.geekbrains.books.presentation.books.BooksFragment

@Module
abstract class BookUiModule {

    @ContributesAndroidInjector
    abstract fun bindBooksFragment(): BooksFragment

    @ContributesAndroidInjector
    abstract fun bindBookFragment(): BookFragment

}