package ru.geekbrains.books.data.book.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class Response (
    @SerializedName("results")
    val results: Results
)

data class Results (
    @SerializedName("lists")
    val lists: List<BookList>
)

data class BookList (
    @SerializedName("books")
    val books: List<Book>
)