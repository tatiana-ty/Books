package ru.geekbrains.books

import android.util.Log
import com.github.terrakok.cicerone.Cicerone
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import io.reactivex.plugins.RxJavaPlugins
import ru.geekbrains.books.di.DaggerBooksComponent
import ru.geekbrains.books.scheduler.DefaultSchedulers

class BooksApp : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<BooksApp> =
        DaggerBooksComponent
            .builder()
            .withContext(applicationContext)
            .apply {
                val cicerone = Cicerone.create()

                withRouter(cicerone.router)
                withNavigatorHolder(cicerone.getNavigatorHolder())
            }
            .withSchedulers(DefaultSchedulers)
            .withRxBus(RxBus())
            .build()

    override fun onCreate() {
        super.onCreate()

        RxJavaPlugins.setErrorHandler { error ->
            Log.e("GLOBAL_ERRORS", error.message.toString())
        }
    }

}