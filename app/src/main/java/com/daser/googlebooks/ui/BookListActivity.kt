package com.daser.googlebooks.ui

import android.arch.lifecycle.Observer
import android.os.Bundle
import com.daser.googlebooks.R
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.EditText
import com.daser.googlebooks.ui.adapters.BookListAdapter
import com.daser.googlebooks.viewmodel.BookListViewModel
import android.text.Editable
import android.text.TextWatcher


class BookListActivity : AppCompatActivity() {
    private lateinit var viewModel: BookListViewModel
    private lateinit var bookListAdapter: BookListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_list)

        val recyclerView = findViewById<RecyclerView>(R.id.book_list)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        viewModel = ViewModelProviders.of(this).get(BookListViewModel::class.java)
        viewModel.init("")

        bookListAdapter = BookListAdapter()
        viewModel.bookList.observe(this, Observer(bookListAdapter::setList))
        recyclerView.adapter = bookListAdapter

        val searchTextView = findViewById<EditText>(R.id.search_text)
        searchTextView.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                search(s.toString())
            }
        })
    }

    fun search(searchText: String) {
        // reset the attachment to the datasource
        viewModel.bookList.removeObservers(this)
        viewModel.setSearchText(searchText)
        viewModel.bookList.observe(this, Observer(bookListAdapter::setList))
    }
}
