package ru.geekbrains.books.presentation.book

import com.github.terrakok.cicerone.Router
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import moxy.MvpPresenter
import ru.geekbrains.books.RxBus
import ru.geekbrains.books.data.book.BooksRepository
import ru.geekbrains.books.data.book.model.Book
import ru.geekbrains.books.scheduler.Schedulers

class BookPresenter @AssistedInject constructor(
    @Assisted("bookTitle") private val bookTitle: String,
    @Assisted("bookAuthor") private val bookAuthor: String,
    private val booksRepository: BooksRepository,
    private val schedulers: Schedulers,
    private val router: Router,
    private val bus: RxBus
): MvpPresenter<BookView>() {

    private var disposables = CompositeDisposable()

    override fun onFirstViewAttach() {
        disposables +=
            booksRepository
                .getBook(bookTitle, bookAuthor)
                .observeOn(schedulers.background())
                .subscribe(
                    ::onGetBookSuccess,
                    ::onGetBookError
                )
    }

    private fun onGetBookSuccess(book: Book) {
        viewState.showBook(book)
    }

    @Suppress("UNUSED_PARAMETER")
    private fun onGetBookError(error: Throwable) {
        viewState.showError(error.message)
        router.exit()
    }

    override fun onDestroy() {
        super.onDestroy()

        disposables.dispose()
    }

}