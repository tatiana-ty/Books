package ru.geekbrains.books.data.storage

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Update
import io.reactivex.Completable
import io.reactivex.Single
import ru.geekbrains.books.data.book.model.Book

@Dao
interface BooksDao {

    @Query("SELECT * FROM books")
    fun getBooks(): Single<List<Book>>

    @Query("SELECT * FROM books WHERE title = :title AND author = :author")
    fun getBook(title: String, author: String): Single<Book>

    @Insert(onConflict = IGNORE)
    fun retain(books: List<Book>): Completable

    @Query("UPDATE books SET favourite = 1 WHERE title = :title AND author = :author")
    fun fav(title: String, author: String): Completable

    @Query("UPDATE books SET favourite = 0 WHERE title = :title AND author = :author")
    fun unFav(title: String, author: String): Completable

    @Query("SELECT * FROM books WHERE favourite = 1")
    fun getFavs(): Single<List<Book>>
}