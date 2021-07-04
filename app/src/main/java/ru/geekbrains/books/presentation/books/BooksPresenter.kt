package ru.geekbrains.books.presentation.books

import com.github.terrakok.cicerone.Router
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import moxy.MvpPresenter
import ru.geekbrains.books.RxBus
import ru.geekbrains.books.data.book.BooksRepository
import ru.geekbrains.books.data.book.model.Book
import ru.geekbrains.books.data.network.NetworkState
import ru.geekbrains.books.data.network.NetworkStateRepository
import ru.geekbrains.books.presentation.book.BookScreen
import ru.geekbrains.books.scheduler.Schedulers

class BooksPresenter(
    private val booksRepository: BooksRepository,
    private val networkStateRepository: NetworkStateRepository,
    private val router: Router,
    private val schedulers: Schedulers,
    private val bus: RxBus
): MvpPresenter<BooksView>() {

    private var disposables =  CompositeDisposable()

    override fun onFirstViewAttach() {
//        disposables += bus.subscribe(UserUpdatedEvent::class) {
//            displayUsers()
//        }

        disposables +=
            networkStateRepository
                .watchForNetworkState()
                .filter { networkState -> networkState == NetworkState.CONNECTED }
                .observeOn(schedulers.main())
                .doOnNext { displayUsers() }
                .subscribeOn(schedulers.background())
                .subscribe()

        displayUsers()
    }

    private fun displayUsers() {
        viewState.showLoading()

        disposables +=
            booksRepository
                .getBooks()
                .map { users -> users.map(BookMapper::map) }
                .observeOn(schedulers.main())
                .subscribeOn(schedulers.background())
                .subscribe(
                    ::onGetBooksSuccess,
                    ::onGetBooksError
                )
    }

    private fun onGetBooksSuccess(books: List<Book>) {
        viewState.showBooks(books)
    }

    private fun onGetBooksError(error: Throwable) {
        viewState.showError(error.message)
    }

    fun displayBook(book: Book) =
        router.navigateTo(BookScreen(book.title))

    override fun onDestroy() {
        super.onDestroy()

        disposables.dispose()
    }

}