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
    private var bookDetailRepository: BookDetailRepository = BookDetailRepository()

//    @Inject // UserRepository parameter is provided by Dagger 2
//    fun BookDetailViewModel(repo: BookDetailRepository) {
//        this.bookDetailRepository = repo
//    }

    fun init(bookId: String) {

        book = bookDetailRepository.getBookDetail(bookId)
    }


}
