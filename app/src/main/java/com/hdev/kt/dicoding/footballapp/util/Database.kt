package com.hdev.kt.dicoding.footballapp.util

import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import com.hdev.kt.dicoding.footballapp.db.database
import com.hdev.kt.dicoding.footballapp.model.Events
import com.hdev.kt.dicoding.footballapp.model.Teams
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.toast

/**
 * Add Event to Favorites
 * @param event Event data
 */
fun Context.addEventToFavorite(event: Events) {
    try {
        database.use {
            insert(
                Events.TABLE_EVENT_FAVORITE,
                Events.ID_EVENT to event.idEvent,
                Events.ID_HOME_TEAM to event.idHomeTeam,
                Events.ID_AWAY_TEAM to event.idAwayTeam,
                Events.SPORT to event.strSport,
                Events.EVENT to event.strEvent,
                Events.HOME_TEAM to event.strHomeTeam,
                Events.AWAY_TEAM to event.strAwayTeam,
                Events.DATE to event.dateEvent,
                Events.TIME to event.strTime,
                Events.HOME_SCORE to event.homeScore,
                Events.AWAY_SCORE to event.awayScore,
                Events.HOME_GOAL_DETAIL to event.homeGoalDetail,
                Events.AWAY_GOAL_DETAIL to event.awayGoalDetail,
                Events.HOME_RED_CARD to event.homeRedCards,
                Events.HOME_YELLOW_CARD to event.homeYellowCards,
                Events.AWAY_RED_CARD to event.awayRedCards,
                Events.AWAY_YELLOW_CARD to event.awayYellowCards,
                Events.HOME_FORMATION to event.homeFormation,
                Events.AWAY_FORMATION to event.awayFormation,
                Events.HOME_GOAL_KEEPER to event.homeGoalKeeper,
                Events.HOME_DEFENSE to event.homeDefense,
                Events.HOME_MIDFIELD to event.homeMidfield,
                Events.HOME_FORWARD to event.homeForward,
                Events.AWAY_GOAL_KEEPER to event.awayKeeper,
                Events.AWAY_DEFENSE to event.awayDefense,
                Events.AWAY_MIDFIELD to event.awayMidfield,
                Events.AWAY_FORWARD to event.awayForward
            )
        }
        toast("Event ${event.strHomeTeam} VS ${event.strAwayTeam} \nAdded to Favorite")
    } catch (e: SQLiteConstraintException) {
        toast(e.printStackTrace().toString())
    }
}

/**
 * Remove Event from Favorites
 * @param id Id Event
 */
fun Context.removeEventFavorite(id: String) {
    try {
        database.use {
            delete(
                Events.TABLE_EVENT_FAVORITE, "(ID_EVENT = {id})",
                "id" to id
            )
        }
        toast("Event removed from Favorite")
    } catch (e: SQLiteConstraintException) {
        toast(e.printStackTrace().toString())
    }
}

/**
 * Get all Favorites from database
 */
fun Context.getEventFavorites(): List<Events> {
    val favorites: MutableList<Events> = mutableListOf()
    favorites.clear()
    database.use {
        val result = select(Events.TABLE_EVENT_FAVORITE)
        val favoriteEvents = result.parseList(classParser<Events>())
        favorites.addAll(favoriteEvents)
    }
    return favorites
}

/**
 * check event is already added to database
 * @param id id Event
 */
fun Context.isEventFavorite(id: String): Boolean {
    var favorite = false
    database.use {
        val result = select(Events.TABLE_EVENT_FAVORITE)
            .whereArgs(
                "(ID_EVENT = {id})",
                "id" to id
            )
        val isFavorite = result.parseList(classParser<Events>())
        if (isFavorite.isNotEmpty()) favorite = true
    }
    return favorite
}

fun Context.addTeamToFavorite(team: Teams) {
    try {
        database.use {
            insert(
                Teams.TABLE_TEAM_FAVORITE,
                Teams.ID_TEAM to team.idTeam,
                Teams.TEAM to team.team,
                Teams.LEAGUE to team.league,
                Teams.MANAGER to team.manager,
                Teams.STADIUM to team.stadium,
                Teams.STADIUM_THUMB to team.stadiumThumb,
                Teams.STADIUM_DESCRIPTION to team.stadiumDescription,
                Teams.STADIUM_LOCATION to team.stadiumLocation,
                Teams.STADIUM_CAPACITY to team.stadiumCapacity,
                Teams.WEBSITE to team.website,
                Teams.FACEBOOK to team.facebook,
                Teams.TWITTER to team.twitter,
                Teams.INSTAGRAM to team.instagram,
                Teams.DESCRIPTION_EN to team.descriptionEN,
                Teams.TEAM_BADGE to team.teamBadge
            )
        }
        toast("Team ${team.team} Added to Favorite")
    } catch (e: SQLiteConstraintException) {
        toast(e.printStackTrace().toString())
    }
}

fun Context.removeTeamFavorite(id: String) {
    try {
        database.use {
            delete(
                Teams.TABLE_TEAM_FAVORITE, "(ID_TEAM = {id})",
                "id" to id
            )
        }
    } catch (e: SQLiteConstraintException) {
        toast(e.printStackTrace().toString())
    }
}

fun Context.getTeamFavorites(): List<Teams> {
    val favorites: MutableList<Teams> = mutableListOf()
    favorites.clear()
    database.use {
        val result = select(Teams.TABLE_TEAM_FAVORITE)
        val favoriteTeams = result.parseList(classParser<Teams>())
        favorites.addAll(favoriteTeams)
    }
    return favorites
}

fun Context.isTeamFavorite(id: String): Boolean {
    var favorite = false
    database.use {
        val result = select(Teams.TABLE_TEAM_FAVORITE).whereArgs(
            "(ID_TEAM = {id})",
            "id" to id
        )
        val isFavorite = result.parseList(classParser<Teams>())
        if (isFavorite.isNotEmpty()) favorite = true
    }
    return favorite
}