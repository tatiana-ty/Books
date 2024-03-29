package ru.geekbrains.books.presentation.book

import androidx.fragment.app.FragmentFactory
import com.github.terrakok.cicerone.androidx.FragmentScreen

class BookScreen(private val bookTitle: String, private val bookAuthor: String): FragmentScreen {

    override fun createFragment(factory: FragmentFactory) =
        BookFragment.newInstance(bookTitle = bookTitle, bookAuthor = bookAuthor)

}