package com.daser.googlebooks.datasource

import android.arch.paging.DataSource
import android.arch.paging.TiledDataSource
import android.util.Log
import com.daser.googlebooks.config.Constants.SUMMARY_FIELDS

import com.daser.googlebooks.data.Book
import com.daser.googlebooks.network.GoogleBooksApi
import com.daser.googlebooks.network.GoogleBooksService

import java.io.IOException
import java.util.ArrayList

/**
 * Created by Dorin on 11/20/2017.
 */

class TDataSource : TiledDataSource<Book>() {

    var searchText = ""

    private var bookService: GoogleBooksService = GoogleBooksApi.createGoogleBooksService()

    //Number of items that this DataSource can provide in total.
    override fun countItems(): Int = DataSource.COUNT_UNDEFINED

    //Called to load items at from the specified position range.
    override fun loadRange(startPosition: Int, count: Int): List<Book> {
        val books = ArrayList<Book>()
        try {
            val response = bookService.getVolumes(searchText, startPosition, count, SUMMARY_FIELDS).execute()
            if (response.isSuccessful && response.code() == 200) {
                if (response.body() != null && response.body()!!.items != null)
                {
                    books.addAll(response.body()!!.items!!)
                }
            } else {
                Log.e("API CALL ERROR", response.message())
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return books
    }
}
