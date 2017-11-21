package com.daser.googlebooks.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Dorin on 11/20/2017.
 */

object GoogleBooksApi {
    fun createGoogleBooksService(): GoogleBooksService {
        val builder = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://www.googleapis.com/books/v1/")

        return builder.build().create(GoogleBooksService::class.java)
    }
}
