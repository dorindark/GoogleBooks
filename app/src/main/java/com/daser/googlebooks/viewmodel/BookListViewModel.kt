package com.daser.googlebooks.viewmodel

import android.app.Application
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.arch.paging.DataSource
import android.arch.paging.LivePagedListProvider
import android.arch.paging.PagedList

import com.daser.googlebooks.config.Constants
import com.daser.googlebooks.data.Book
import com.daser.googlebooks.datasource.TDataSource

/**
 * Created by Dorin on 11/20/2017.
 */

class BookListViewModel : ViewModel() {
    lateinit var bookList: LiveData<PagedList<Book>>
    lateinit internal var tDataSource: TDataSource

    fun init(searchText : String) {
        bookList = createDataSource(searchText)
    }

    fun createDataSource (searchText : String) :  LiveData<PagedList<Book>>
    {
        return object : LivePagedListProvider<Int, Book>() {
            override fun createDataSource(): DataSource<Int, Book> {
                tDataSource = TDataSource()
                tDataSource.searchText = searchText
                return tDataSource
            }
        }.create(0, PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(Constants.PAGE_SIZE)
                .setInitialLoadSizeHint(Constants.PAGE_SIZE)
                .build())
    }

    fun setSearchText(searchText: String) {
        bookList = createDataSource(searchText)
    }
}
