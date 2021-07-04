package ru.geekbrains.books.presentation.books.adapter

import android.view.LayoutInflater.from
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import ru.geekbrains.books.data.book.model.Book
import ru.geekbrains.books.databinding.ViewBookBinding

class BooksAdapter(private val delegate: Delegate?): ListAdapter<Book, BookViewHolder>(BookDiffing) {

    interface Delegate {

        fun onBookPicked(book: Book)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder =
        BookViewHolder(
            ViewBookBinding
                .inflate(from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) =
        holder.bind(getItem(position), delegate)

}