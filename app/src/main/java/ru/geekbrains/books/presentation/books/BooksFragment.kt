package ru.geekbrains.books.presentation.books

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.github.terrakok.cicerone.Router
import moxy.ktx.moxyPresenter
import ru.geekbrains.books.R.layout.fragment_books
import ru.geekbrains.books.presentation.abs.AbsFragment
import ru.geekbrains.books.RxBus
import ru.geekbrains.books.arguments
import ru.geekbrains.books.data.book.BooksRepository
import ru.geekbrains.books.data.book.model.Book
import ru.geekbrains.books.data.network.NetworkStateRepository
import ru.geekbrains.books.databinding.FragmentBooksBinding
import ru.geekbrains.books.presentation.books.adapter.BooksAdapter
import ru.geekbrains.books.scheduler.Schedulers
import javax.inject.Inject

class BooksFragment : AbsFragment(fragment_books), BooksView, BooksAdapter.Delegate {

    companion object {

        fun newInstance(): Fragment =
            BooksFragment()
                .arguments()

    }

    @Inject
    lateinit var booksRepository: BooksRepository

    @Inject
    lateinit var networkStateRepository: NetworkStateRepository

    @Inject
    lateinit var schedulers: Schedulers

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var bus: RxBus

    private val presenter: BooksPresenter by moxyPresenter {
        BooksPresenter(
            booksRepository = booksRepository,
            networkStateRepository = networkStateRepository,
            router = router,
            schedulers = schedulers, bus
        )
    }

    private var viewBinding: FragmentBooksBinding? = null

    private val booksAdapter = BooksAdapter(delegate = this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        FragmentBooksBinding
            .inflate(inflater, container, false)
            .apply {
                viewBinding = this
                viewBinding?.books?.adapter = booksAdapter
            }
            .root

    override fun showLoading() {
        viewBinding?.loading?.visibility = VISIBLE
        viewBinding?.books?.visibility = GONE
    }

    override fun showBooks(books: List<Book>) {
        booksAdapter.submitList(books)

        viewBinding?.loading?.visibility = GONE
        viewBinding?.books?.visibility = VISIBLE
    }

    override fun showError(message: String?) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

    override fun onBookPicked(book: Book) =
        presenter.displayBook(book)

    override fun onDestroyView() {
        super.onDestroyView()
        viewBinding = null
    }

}