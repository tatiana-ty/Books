package ru.geekbrains.books.di

import android.content.Context
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import ru.geekbrains.books.BooksApp
import ru.geekbrains.books.RxBus
import ru.geekbrains.books.di.module.BookModule
import ru.geekbrains.books.di.module.MainModule
import ru.geekbrains.books.di.module.NetworkStateModule
import ru.geekbrains.books.di.module.StorageModule
import ru.geekbrains.books.scheduler.Schedulers
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, MainModule::class, StorageModule::class, NetworkStateModule::class, BookModule::class])
interface BooksComponent : AndroidInjector<BooksApp> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun withContext(context: Context): Builder

        @BindsInstance
        fun withRouter(router: Router): Builder

        @BindsInstance
        fun withNavigatorHolder(navigatorHolder: NavigatorHolder): Builder

        @BindsInstance
        fun withSchedulers(schedulers: Schedulers): Builder

        @BindsInstance
        fun withRxBus(bus: RxBus): Builder

        fun build(): BooksComponent

    }

}