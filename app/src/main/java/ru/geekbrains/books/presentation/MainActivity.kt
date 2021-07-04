package ru.geekbrains.books.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import ru.geekbrains.books.R
import ru.geekbrains.books.R.layout.activity_main
import ru.geekbrains.books.RxBus
import ru.geekbrains.books.data.network.NetworkState
import ru.geekbrains.books.data.network.NetworkStateObservable
import ru.geekbrains.books.presentation.abs.AbsActivity
import ru.geekbrains.books.presentation.books.BooksScreen
import javax.inject.Inject

class MainActivity : AbsActivity(activity_main) {

    private val navigator = AppNavigator(activity = this, R.id.screen_container)

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    @Inject
    lateinit var bus: RxBus

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        savedInstanceState ?: router.newRootScreen(BooksScreen)
    }

    private lateinit var disposables: CompositeDisposable

    override fun onStart() {
        super.onStart()

        disposables = CompositeDisposable()
        disposables +=
            NetworkStateObservable(this)
                .skip(1)
                .subscribe(
                    ::onNetworkStateChanged,
                    ::onNetworkStateError
                )

//        disposables += bus.subscribe(UserUpdatedEvent::class) { event ->
//            Toast.makeText(this, "UserUpdated: ${event.userId}", Toast.LENGTH_SHORT).show()
//        }
    }

    private fun onNetworkStateChanged(networkState: NetworkState) {
        Toast.makeText(this, "NetworkStateChanged: ${networkState.name}", Toast.LENGTH_LONG).show()
    }

    private fun onNetworkStateError(error: Throwable) {
        Toast.makeText(this, "NetworkStateError: ${error.message}", Toast.LENGTH_SHORT).show()
    }

    override fun onStop() {
        super.onStop()

        disposables.dispose()
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

}
