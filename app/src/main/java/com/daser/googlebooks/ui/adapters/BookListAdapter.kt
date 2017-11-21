package com.daser.googlebooks.ui.adapters

import android.arch.paging.PagedListAdapter
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.daser.googlebooks.R
import com.daser.googlebooks.data.Book

/**
 * Created by Dorin on 11/20/2017.
 */

class BookListAdapter : PagedListAdapter<Book, BookListAdapter.BookItemViewHolder>(Book.DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_book_list, parent, false)
        return BookItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookItemViewHolder, position: Int) {
        val book = getItem(position)
        if (book != null) {
            holder.bindTo(book)
        }
    }

    class BookItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var bookNameView: TextView = itemView.findViewById(R.id.book_name)
        private var bookAuthorsView: TextView = itemView.findViewById(R.id.book_authors)

        fun bindTo(book: Book?) {
            var bookName = ""
            var bookAuthors = ""

            if (book?.volumeInfo != null) {
                if (book.volumeInfo!!.title != null) {
                    bookName = book.volumeInfo!!.title.toString()
                }

                if (book.volumeInfo!!.authors != null) {
                    bookAuthors = book.volumeInfo!!.authors!!.toString()
                }

            }

            bookNameView.text = bookName
            bookAuthorsView.text = bookAuthors
        }
    }
}

