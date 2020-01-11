package com.hdev.kt.dicoding.footballapp.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TableResponse(
    @SerializedName("table")
    val tables: List<Tables>
) : Parcelable