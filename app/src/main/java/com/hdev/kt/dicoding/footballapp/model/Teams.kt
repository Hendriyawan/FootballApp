package com.hdev.kt.dicoding.footballapp.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Teams(
    val id: Long? = null,
    @SerializedName("idTeam")
    var idTeam: String? = null,

    @SerializedName("strTeam")
    var team: String? = null,

    @SerializedName("strLeague")
    var league: String? = null,

    @SerializedName("strManager")
    var manager: String? = null,

    @SerializedName("strStadium")
    var stadium: String? = null,

    @SerializedName("strStadiumThumb")
    var stadiumThumb: String? = null,

    @SerializedName("strStadiumDescription")
    var stadiumDescription: String? = null,

    @SerializedName("strStadiumLocation")
    var stadiumLocation: String? = null,

    @SerializedName("intStadiumCapacity")
    var stadiumCapacity: String? = null,

    @SerializedName("strWebsite")
    var website: String? = null,

    @SerializedName("strFacebook")
    var facebook: String? = null,

    @SerializedName("strTwitter")
    var twitter: String? = null,

    @SerializedName("strInstagram")
    var instagram: String? = null,

    @SerializedName("strDescriptionEN")
    var descriptionEN: String? = null,

    @SerializedName("strTeamBadge")
    var teamBadge: String? = null

) : Parcelable {
    companion object {
        const val TABLE_TEAM_FAVORITE: String = "TABLE_TEAM_FAVORITE"
        const val ID: String = "ID_"
        const val ID_TEAM: String = "ID_TEAM"
        const val TEAM: String = "TEAM"
        const val LEAGUE: String = "LEAGUE"
        const val MANAGER: String = "manager"
        const val STADIUM: String = "stadium"
        const val STADIUM_THUMB = "STADIUM_THUMB"
        const val STADIUM_DESCRIPTION: String = "STADIUM_DESCRIPTION"
        const val STADIUM_LOCATION: String = "STADIUM_LOCATION"
        const val STADIUM_CAPACITY: String = "STADIUM_CAPACITY"
        const val WEBSITE: String = "WEBSITE"
        const val FACEBOOK: String = "FACEBOOK"
        const val TWITTER: String = "TWITTER"
        const val INSTAGRAM: String = "INSTAGRAM"
        const val DESCRIPTION_EN: String = "DESCRIPTION_EN"
        const val TEAM_BADGE: String = "TEAM_BADGE"
    }
}