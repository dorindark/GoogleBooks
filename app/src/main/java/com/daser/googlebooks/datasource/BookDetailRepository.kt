package com.daser.googlebooks.datasource

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.daser.googlebooks.data.Book
import com.daser.googlebooks.network.GoogleBooksService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


/**
 * Created by Dorin on 11/20/2017.
 */

class BookDetailRepository(private val booksService: GoogleBooksService) {

    fun getBookDetail(bookId: String): LiveData<Book> {
        val data = MutableLiveData<Book>()

        booksService.getBookDetailById(bookId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result -> data.value = result }, // on success
                        { data.value = Book() } // on error, also log it
                )
        return data
    }
}
