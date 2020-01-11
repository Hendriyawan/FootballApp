package com.hdev.kt.dicoding.footballapp.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Tables(
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("teamid")
    val teamId: String? = null,
    @SerializedName("played")
    val played: Int? = null,
    @SerializedName("goalsfor")
    val goalsFor: Int? = null,
    @SerializedName("goalsagainst")
    val goalsAgainst: Int? = null,
    @SerializedName("goalsdifference")
    val goalsdifference: Int? = null,
    @SerializedName("win")
    val win: Int? = null,
    @SerializedName("draw")
    val draw: Int? = null,
    @SerializedName("loss")
    val loss: Int? = null,
    @SerializedName("total")
    val total: Int? = null
) : Parcelable