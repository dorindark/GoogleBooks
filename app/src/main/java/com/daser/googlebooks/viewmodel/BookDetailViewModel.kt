package com.daser.googlebooks.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.daser.googlebooks.data.Book
import com.daser.googlebooks.datasource.BookDetailRepository


/**
 * Created by Dorin on 11/20/2017.
 */

class BookDetailViewModel : ViewModel() {
    lateinit var book: LiveData<Book>

    //  @Inject // UserRepository parameter is provided by Dagger 2 for testing the ViewModel in isolation
    fun init(bookDetailRepository: BookDetailRepository, bookId: String) {
        book = bookDetailRepository.getBookDetail(bookId)
    }


}
