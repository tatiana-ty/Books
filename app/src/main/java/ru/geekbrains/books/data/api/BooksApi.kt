package ru.geekbrains.books.data.api

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import ru.geekbrains.books.data.book.model.Book
import ru.geekbrains.books.data.book.model.Response

interface BooksApi {

    @GET("/svc/books/v3/lists/overview.json")
    fun getBooks(
        @Query("api-key") key: String = "9AHMq10FH8cD9tgYIGyuCTShZC6FEUI9"
    ): Single<Response>

}