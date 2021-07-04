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
    @Assisted("book") private val book: String,
    private val booksRepository: BooksRepository,
    private val schedulers: Schedulers,
    private val router: Router,
    private val bus: RxBus
): MvpPresenter<BookView>() {

    private var disposables = CompositeDisposable()

    override fun onFirstViewAttach() {
//        disposables +=
//            booksRepository
//                .getBooks(userId)
//                .observeOn(schedulers.main())
//                .doOnNext { user -> bus.publish(UserUpdatedEvent(user.id)) }
//                .subscribeOn(schedulers.background())
//                .subscribe(
//                    ::onFetchUserByIdSuccess,
//                    ::onFetchUserByIdError
//                )
    }

    private fun onGetBookByNameSuccess(book: Book) {
        viewState.showBook(book)
    }

    @Suppress("UNUSED_PARAMETER")
    private fun onGetBookByNameError(error: Throwable) {
        viewState.showError(error.message)

        /*
         * Возвращаемся на предыдущий
         * экран в случае ошибки.
         */
        router.exit()
    }

    override fun onDestroy() {
        super.onDestroy()

        disposables.dispose()
    }

}