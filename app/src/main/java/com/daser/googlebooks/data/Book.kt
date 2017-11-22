package com.daser.googlebooks.data

import android.support.v7.recyclerview.extensions.DiffCallback

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by Dorin on 11/20/2017.
 */

data class Book(
        @SerializedName("id")
        @Expose
        var id: String = "",

        @SerializedName("volumeInfo")
        @Expose
        var volumeInfo: VolumeInfo = VolumeInfo()) {

    companion object {

        @JvmField
        val DIFF_CALLBACK: DiffCallback<Book> = object : DiffCallback<Book>() {
            override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean =
                    oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean =
                    oldItem == newItem
        }
    }
}
