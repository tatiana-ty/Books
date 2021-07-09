package ru.geekbrains.books.data.book.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "books")
data class Book (
    @PrimaryKey
    @ColumnInfo(name = "title")
    @SerializedName("title")
    val title: String,
    @ColumnInfo(name = "author")
    @SerializedName("author")
    val author: String,
    @ColumnInfo(name = "book_image")
    @SerializedName("book_image")
    val book_image: String,
    @ColumnInfo(name = "description")
    @SerializedName("description")
    val description: String,
    @ColumnInfo(name = "publisher")
    @SerializedName("publisher")
    val publisher: String,
    @ColumnInfo(name = "favourite")
    @Transient val favourite: Int = 0
)