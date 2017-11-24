package com.daser.googlebooks.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.arch.paging.DataSource
import android.arch.paging.LivePagedListProvider
import android.arch.paging.PagedList
import com.daser.googlebooks.config.Constants
import com.daser.googlebooks.data.Book
import com.daser.googlebooks.datasource.BookListRepository

/**
 * Created by Dorin on 11/20/2017.
 */

class BookListViewModel : ViewModel() {
    lateinit var bookList: LiveData<PagedList<Book>>
    lateinit internal var dataRepository: BookListRepository

    // TODO
    //  @Inject // BookListRepository parameter should be provided by Dagger 2 for testing the ViewModel in isolation
    fun init(bookListRepository: BookListRepository, searchText: String) {
        dataRepository = bookListRepository
        setSearchText(searchText)
    }

    fun setSearchText(searchText: String) {
        bookList = createDataSource(searchText)
    }

    private fun createDataSource(searchText: String): LiveData<PagedList<Book>> =
            object : LivePagedListProvider<Int, Book>() {
                override fun createDataSource(): DataSource<Int, Book> {
                    dataRepository.searchText = searchText
                    return dataRepository
                }
            }.create(0, PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setPageSize(Constants.PAGE_SIZE)
                    .setInitialLoadSizeHint(Constants.PAGE_SIZE)
                    .build())
}
