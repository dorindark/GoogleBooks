package com.daser.googlebooks.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class VolumeInfo {
    @SerializedName("title")
    @Expose
    var title: String? = null
    @SerializedName("subtitle")
    @Expose
    var subtitle: String? = null
    @SerializedName("authors")
    @Expose
    var authors: List<String>? = null
    @SerializedName("publisher")
    @Expose
    var publisher: String? = null
    @SerializedName("publishedDate")
    @Expose
    var publishedDate: String? = null
    @SerializedName("description")
    @Expose
    var description: String? = null

    @SerializedName("pageCount")
    @Expose
    var pageCount: Int? = null

    @SerializedName("categories")
    @Expose
    var categories: List<String>? = null
    @SerializedName("averageRating")
    @Expose
    var averageRating: Double? = null
    @SerializedName("language")
    @Expose
    var language: String? = null
    @SerializedName("previewLink")
    @Expose
    var previewLink: String? = null
    @SerializedName("infoLink")
    @Expose
    var infoLink: String? = null
}
