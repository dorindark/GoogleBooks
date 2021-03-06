package com.daser.googlebooks.datasource

import android.arch.paging.DataSource
import android.arch.paging.TiledDataSource
import com.daser.googlebooks.config.Constants.SUMMARY_FIELDS
import com.daser.googlebooks.data.Book
import com.daser.googlebooks.data.BookList
import com.daser.googlebooks.network.GoogleBooksService
import io.reactivex.Scheduler

/**
 * Created by Dorin on 11/20/2017.
 */

class BookListRepository(private val booksService: GoogleBooksService,
                         private val observeScheduler: Scheduler,
                         private val subscribeScheduler: Scheduler) : TiledDataSource<Book>() {

    var searchText = ""

    //Number of items that this DataSource can provide in total.
    override fun countItems(): Int = DataSource.COUNT_UNDEFINED

    //Called to load items at from the specified position range.
    override fun loadRange(startPosition: Int, count: Int): List<Book> {
        val books = ArrayList<Book>()

        // this is blocking in order to provide results back to loadRange which is sync (by Google design)
        try {
            books.addAll(booksService.getBooks(searchText, startPosition, count, SUMMARY_FIELDS)
                    .subscribeOn(subscribeScheduler)
                    .observeOn(observeScheduler)
                    .map(BookList::items)
                    .blockingFirst())
        } catch (e: Exception) {
            // log error
            e.printStackTrace()
        }

        return books
    }
}
