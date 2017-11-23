package com.daser.googlebooks.ui.adapters

import android.arch.paging.PagedListAdapter
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.daser.googlebooks.R
import com.daser.googlebooks.config.Constants
import com.daser.googlebooks.data.Book
import com.daser.googlebooks.ui.BookDetailActivity
import android.content.Intent



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
        getItem(position)?.let { holder.bindTo(it) }
    }

    class BookItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var bookNameView: TextView = itemView.findViewById(R.id.book_name)
        private var bookAuthorsView: TextView = itemView.findViewById(R.id.book_authors)

        init {
            itemView.setOnClickListener({
                if (itemView.tag != null) {

                    // open detail view activity
                    val intent = Intent(itemView.context, BookDetailActivity::class.java)
                    intent.putExtra(Constants.BOOK_DETAIL_KEY, itemView.tag as String)
                    itemView.context.startActivity(intent)
                }
            })
        }

        fun bindTo(book: Book) {
            itemView.tag = book.id
            bookNameView.text = book.volumeInfo.title
            bookAuthorsView.text = book.volumeInfo.authors.toString()
        }
    }
}

