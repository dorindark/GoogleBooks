package com.daser.googlebooks.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Html
import android.widget.ImageView
import android.widget.TextView
import com.daser.googlebooks.R
import com.daser.googlebooks.config.Constants.BOOK_DETAIL_KEY
import com.daser.googlebooks.data.Book
import com.daser.googlebooks.datasource.BookDetailRepository
import com.daser.googlebooks.network.GoogleBooksServiceInstance
import com.daser.googlebooks.viewmodel.BookDetailViewModel
import com.squareup.picasso.Picasso
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class BookDetailActivity : AppCompatActivity() {

    private lateinit var viewModel: BookDetailViewModel
    private lateinit var bookImageView: ImageView
    private lateinit var bookTitleView: TextView
    private lateinit var bookAuthorsView: TextView
    private lateinit var bookDescriptionView: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_detail)

        bookImageView = findViewById(R.id.book_detail_image)
        bookTitleView = findViewById(R.id.book_detail_title)
        bookAuthorsView = findViewById(R.id.book_detail_authors)
        bookDescriptionView = findViewById(R.id.book_detail_description)

        viewModel = ViewModelProviders.of(this).get(BookDetailViewModel::class.java)

        intent.getStringExtra(BOOK_DETAIL_KEY)?.let {
            val repository = BookDetailRepository(GoogleBooksServiceInstance, AndroidSchedulers.mainThread(), Schedulers.io())
            viewModel.init(repository, it)
            // observe
            viewModel.book.observe(this, Observer { updateUI(it ?: Book()) })
        }
    }

    private fun updateUI(book: Book) {
        Picasso.with(this).load(book.volumeInfo.imageLinks.small).into(bookImageView);
        bookTitleView.text = book.volumeInfo.title
        bookAuthorsView.text = book.volumeInfo.authors.toString()
        if (VERSION.SDK_INT >= VERSION_CODES.N) {
            bookDescriptionView.text = Html.fromHtml(book.volumeInfo.description, Html.FROM_HTML_MODE_LEGACY)
        } else {
            @Suppress("DEPRECATION")
            bookDescriptionView.text = Html.fromHtml(book.volumeInfo.description)
        }
    }
}
