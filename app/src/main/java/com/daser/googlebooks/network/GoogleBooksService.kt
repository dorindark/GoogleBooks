package com.daser.googlebooks.network

import com.daser.googlebooks.data.Book
import com.daser.googlebooks.data.BookList
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Dorin on 11/20/2017.
 */

val GoogleBooksServiceInstance by lazy {
    GoogleBooksService.create()
}

interface GoogleBooksService {
    @GET("volumes")
    fun getBooks(@Query("q") query: String,
                 @Query("startIndex") startIndex: Int,
                 @Query("maxResults") maxResults: Int,
                 @Query("fields") fields: String): Call<BookList>

    @GET("volumes/{volumeId}")
    fun getBookDetailById(@Path("volumeId") volumeId: String): Observable<Book>

    companion object {
        fun create(): GoogleBooksService {

            val builder = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("https://www.googleapis.com/books/v1/")
                    .build()

            return builder.create(GoogleBooksService::class.java)
        }
    }
}

