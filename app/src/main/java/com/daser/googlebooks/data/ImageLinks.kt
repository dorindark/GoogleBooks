package com.daser.googlebooks.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ImageLinks(
        @SerializedName("small")
        @Expose
        var small: String = ""
)