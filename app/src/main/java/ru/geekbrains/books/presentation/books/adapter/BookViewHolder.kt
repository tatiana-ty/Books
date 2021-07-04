package ru.geekbrains.books.presentation.books.adapter

import androidx.recyclerview.widget.RecyclerView
import ru.geekbrains.books.click
import ru.geekbrains.books.data.book.model.Book
import ru.geekbrains.books.databinding.ViewBookBinding
import ru.geekbrains.books.setImageFromUri

class BookViewHolder(
    private val viewBinding: ViewBookBinding
): RecyclerView.ViewHolder(viewBinding.root) {

    fun bind(book: Book, delegate: BooksAdapter.Delegate?) {
        with(viewBinding) {
            bookImage.setImageFromUri(book.book_image)
            bookTitle.text = book.title

            root.click { delegate?.onBookPicked(book) }
        }
    }

}