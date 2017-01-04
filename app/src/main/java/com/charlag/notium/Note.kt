package com.charlag.notium

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by eiolv on 25/12/2016.
 */

data class Note(val content: String) : Parcelable {

    companion object CREATOR : Parcelable.Creator<Note> {
        override fun newArray(size: Int): Array<out Note?> {
            return kotlin.arrayOfNulls(size)
        }

        override fun createFromParcel(parcel: Parcel): Note {
            return Note(parcel.readString())
        }

    }

    override fun writeToParcel(out: Parcel, flags: Int) {
        out.writeString(content)
    }

    override fun describeContents(): Int {
        return 0
    }
}