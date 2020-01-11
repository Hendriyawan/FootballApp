package com.hdev.kt.dicoding.footballapp.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Events(
    val id: Long? = null,

    @SerializedName("idEvent")
    var idEvent: String? = null,

    @SerializedName("idHomeTeam")
    var idHomeTeam: String? = null,

    @SerializedName("idAwayTeam")
    var idAwayTeam: String? = null,

    @SerializedName("strSport")
    var strSport: String? = null,

    @SerializedName("strEvent")
    var strEvent: String? = null,

    @SerializedName("strHomeTeam")
    var strHomeTeam: String? = null,

    @SerializedName("strAwayTeam")
    var strAwayTeam: String? = null,

    @SerializedName("dateEvent")
    var dateEvent: String? = null,

    @SerializedName("strTime")
    var strTime: String? = null,

    @SerializedName("intHomeScore")
    var homeScore: String? = null,

    @SerializedName("intAwayScore")
    var awayScore: String? = null,

    @SerializedName("strHomeGoalDetails")
    var homeGoalDetail: String? = null,

    @SerializedName("strAwayGoalDetails")
    var awayGoalDetail: String? = null,

    @SerializedName("strHomeRedCards")
    var homeRedCards: String? = null,

    @SerializedName("strHomeYellowCards")
    var homeYellowCards: String? = null,

    @SerializedName("strAwayRedCards")
    var awayRedCards: String? = null,

    @SerializedName("strAwayYellowCards")
    var awayYellowCards: String? = null,

    @SerializedName("strHomeFormation")
    var homeFormation: String? = null,

    @SerializedName("strAwayFormation")
    var awayFormation: String? = null,

    @SerializedName("strHomeLineupGoalkeeper")
    var homeGoalKeeper: String? = null,

    @SerializedName("strHomeLineupDefense")
    var homeDefense: String? = null,

    @SerializedName("strHomeLineupMidfield")
    var homeMidfield: String? = null,

    @SerializedName("strHomeLineupForward")
    var homeForward: String? = null,

    @SerializedName("strAwayLineupGoalkeeper")
    var awayKeeper: String? = null,

    @SerializedName("strAwayLineupDefense")
    var awayDefense: String? = null,

    @SerializedName("strAwayLineupMidfield")
    var awayMidfield: String? = null,

    @SerializedName("strAwayLineupForward")
    var awayForward: String? = null

) : Parcelable {
    companion object {
        const val TABLE_EVENT_FAVORITE: String = "TABLE_EVENT_FAVORITE"
        const val ID = "ID_"
        const val ID_EVENT: String = "ID_EVENT"
        const val ID_HOME_TEAM: String = "ID_HOME_TEAM"
        const val ID_AWAY_TEAM: String = "ID_AWAY_TEAM"
        const val SPORT: String = "SPORT"
        const val EVENT: String = "EVENT"
        const val HOME_TEAM: String = "HOME_TEAM"
        const val AWAY_TEAM: String = "AWAY_TEAM"
        const val DATE: String = "DATE_EVENT"
        const val TIME: String = "TIME_EVENT"
        const val HOME_SCORE: String = "HOME_SCORE"
        const val AWAY_SCORE: String = "AWAY_SCORE"
        const val HOME_GOAL_DETAIL: String = "HOME_GOAL_DETAIL"
        const val AWAY_GOAL_DETAIL: String = "AWAY_GOAL_DETAIL"
        const val HOME_RED_CARD: String = "HOME_RED_CARD"
        const val HOME_YELLOW_CARD: String = "HOME_YELLOW_CARD"
        const val AWAY_RED_CARD: String = "AWAY_RED_CARD"
        const val AWAY_YELLOW_CARD: String = "AWAY_YELLOW_CARD"
        const val HOME_FORMATION: String = "HOME_FORMATION"
        const val AWAY_FORMATION: String = "AWAY_FORMATION"
        const val HOME_GOAL_KEEPER: String = "HOME_GOAL_KEEPER"
        const val HOME_DEFENSE: String = "HOME_DEFENSE"
        const val HOME_MIDFIELD: String = "HOME_MIDFIELD"
        const val HOME_FORWARD: String = "HOME_FORWARD"
        const val AWAY_GOAL_KEEPER: String = "AWAY_GOAL_KEEPER"
        const val AWAY_DEFENSE: String = "AWAY_DEFENSE"
        const val AWAY_MIDFIELD: String = "AWAY_MIDFIELD"
        const val AWAY_FORWARD: String = "AWAY_FORWARD"
    }
}