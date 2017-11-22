package com.daser.googlebooks.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class VolumeInfo(

        @SerializedName("title")
        @Expose
        var title: String = "",

        @SerializedName("subtitle")
        @Expose
        var subtitle: String = "",

        @SerializedName("authors")
        @Expose
        var authors: List<String> = ArrayList(),

        @SerializedName("publisher")
        @Expose
        var publisher: String = "",

        @SerializedName("publishedDate")
        @Expose
        var publishedDate: String = "",

        @SerializedName("description")
        @Expose
        var description: String = "",

        @SerializedName("pageCount")
        @Expose
        var pageCount: Int = 0,

        @SerializedName("categories")
        @Expose
        var categories: List<String> = ArrayList(),

        @SerializedName("averageRating")
        @Expose
        var averageRating: Double = 0.0,

        @SerializedName("language")
        @Expose
        var language: String = "",

        @SerializedName("previewLink")
        @Expose
        var previewLink: String = "",

        @SerializedName("infoLink")
        @Expose
        var infoLink: String = "")
