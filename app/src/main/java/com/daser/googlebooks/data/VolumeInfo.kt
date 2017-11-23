package com.daser.googlebooks.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class VolumeInfo(

        @SerializedName("title")
        @Expose
        var title: String = "",

        @SerializedName("authors")
        @Expose
        var authors: List<String> = ArrayList(),

        @SerializedName("description")
        @Expose
        var description: String = "",

        @SerializedName("pageCount")
        @Expose
        var pageCount: Int = 0,

        @SerializedName("imageLinks")
        @Expose
        var imageLinks: ImageLinks = ImageLinks()
)
