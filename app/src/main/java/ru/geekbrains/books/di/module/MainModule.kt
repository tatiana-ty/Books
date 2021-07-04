package ru.geekbrains.books.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ru.geekbrains.books.presentation.MainActivity

@Module
abstract class MainModule {

    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity

}