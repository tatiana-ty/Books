package ru.geekbrains.books.data.storage

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Single
import ru.geekbrains.books.data.book.model.Book

@Dao
interface BooksDao {

    @Query("SELECT * FROM books")
    fun getBooks(): Single<List<Book>>

    @Query("SELECT * FROM books WHERE title = :title AND author = :author")
    fun getBook(title: String, author: String): Single<Book>

    @Insert(onConflict = REPLACE)
    fun retain(books: List<Book>): Completable

}