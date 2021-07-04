package ru.geekbrains.books.presentation.books

import androidx.fragment.app.FragmentFactory
import com.github.terrakok.cicerone.androidx.FragmentScreen

object BooksScreen: FragmentScreen {

    override fun createFragment(factory: FragmentFactory) =
        BooksFragment.newInstance()

}