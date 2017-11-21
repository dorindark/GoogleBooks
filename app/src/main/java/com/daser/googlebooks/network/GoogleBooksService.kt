package com.daser.googlebooks.network

import com.daser.googlebooks.data.Book
import com.daser.googlebooks.data.BookList

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Dorin on 11/20/2017.
 */

interface GoogleBooksService {
    //@Headers({"Accept-Encoding: gzip", "User-Agent: GoogleBooksService (gzip)"})
    @GET("volumes")
    fun getVolumes(@Query("q") query: String,
                   @Query("startIndex") startIndex: Int,
                   @Query("maxResults") maxResults: Int,
                   @Query("fields") fields: String): Call<BookList>

    @GET("volumes/{volumeId}")
    fun getVolumeById(@Path("volumeId") volumeId: String): Call<Book>
}

