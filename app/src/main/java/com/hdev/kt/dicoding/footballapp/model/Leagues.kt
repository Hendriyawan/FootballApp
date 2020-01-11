package com.hdev.kt.dicoding.footballapp.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Leagues(
    @SerializedName("idLeague")
    var idLeague: String? = null,

    @SerializedName("strLeague")
    var strLeague: String? = null,

    @SerializedName("strDescriptionEN")
    var strDescriptionEN: String? = null,

    @SerializedName("strFanart1")
    var strFanart1: String? = null,

    @SerializedName("strFanart2")
    var strFanart2: String? = null,

    @SerializedName("strFanart3")
    var strFanart3: String? = null,

    @SerializedName("strFanart4")
    var strFanart4: String? = null,

    @SerializedName("strPoster")
    var strPoster: String? = null,

    @SerializedName("strBadge")
    var strBadge: String? = null

) : Parcelable