package com.daser.googlebooks.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class BookList {

    @SerializedName("totalItems")
    @Expose
    var totalItems: Int? = null
    @SerializedName("items")
    @Expose
    var items: List<Book>? = null

}
