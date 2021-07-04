package ru.geekbrains.books.presentation.books.adapter

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import ru.geekbrains.books.data.book.model.Book

object BookDiffing : DiffUtil.ItemCallback<Book>() {

    private val payload = Any()

    override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean {
        return oldItem.title == newItem.title
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean {
        return oldItem == newItem
    }

    override fun getChangePayload(oldItem: Book, newItem: Book) = payload

}

