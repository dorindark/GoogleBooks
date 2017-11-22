package com.daser.googlebooks.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class BookList(

        @SerializedName("totalItems")
        @Expose
        var totalItems: Int = 0,

        @SerializedName("items")
        @Expose
        var items: List<Book> = ArrayList())
