package ru.geekbrains.books.presentation.book

import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import moxy.ktx.moxyPresenter
import ru.geekbrains.books.R
import ru.geekbrains.books.R.layout.fragment_book
import ru.geekbrains.books.arguments
import ru.geekbrains.books.data.book.model.Book
import ru.geekbrains.books.databinding.FragmentBookBinding
import ru.geekbrains.books.presentation.abs.AbsFragment
import ru.geekbrains.books.setImageFromUri
import javax.inject.Inject

class BookFragment : AbsFragment(fragment_book), BookView {

    companion object {

        private const val ARG_BOOK_TITLE = "bookTitle"
        private const val ARG_BOOK_AUTHOR = "bookAuthor"

        fun newInstance(bookTitle: String, bookAuthor: String): Fragment =
            BookFragment()
                .arguments(ARG_BOOK_TITLE to bookTitle, ARG_BOOK_AUTHOR to bookAuthor)

    }

    private val bookTitle: String by lazy {
        arguments?.getString(ARG_BOOK_TITLE) ?: ""
    }

    private val bookAuthor: String by lazy {
        arguments?.getString(ARG_BOOK_AUTHOR) ?: ""
    }

    @Inject
    lateinit var bookPresenterFactory: BookPresenterFactory

    @Suppress("unused")
    private val presenter: BookPresenter by moxyPresenter {
        bookPresenterFactory.create(bookTitle, bookAuthor)
    }

    private var viewBinding: FragmentBookBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        FragmentBookBinding
            .inflate(inflater, container, false)
            .apply { viewBinding = this }
            .root

    override fun showBook(book: Book) {
        with(viewBinding!!) {
            title.text = book.title
            author.text = book.author
            publisher.text = book.publisher
            description.text = book.description
            image.setImageFromUri(book.book_image)
            onFavChanged(book)
        }
    }

    override fun onFavChanged(book: Book) {
        viewBinding?.favourite?.setOnClickListener {
            when (book.favourite) {
                0 -> presenter.fav()
                else -> presenter.unFav()
            }
        }
        viewBinding?.favourite?.setImageDrawable(
            when (book.favourite) {
                1 -> resources.getDrawable(R.drawable.ic_baseline_favorite_24, context?.theme)
                else -> resources.getDrawable(R.drawable.ic_baseline_favorite_border_24, context?.theme)
            }
        )
    }

    override fun showError(message: String?) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewBinding = null
    }

}