package com.daser.googlebooks.data

import android.support.v7.recyclerview.extensions.DiffCallback

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by Dorin on 11/20/2017.
 */

class Book {

    @SerializedName("id")
    @Expose
    var id: String? = null

    @SerializedName("volumeInfo")
    @Expose
    var volumeInfo: VolumeInfo? = null

//    override fun equals(obj: Any?): Boolean {
//        if (obj == this) {
//            return true
//        }
//
//        val book = obj as Book?
//
//        return (book!!.id == this.id && book.volumeInfo!!.title == this.volumeInfo!!.title
//                && book.volumeInfo!!.authors == this.volumeInfo!!.authors)
//    }

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
