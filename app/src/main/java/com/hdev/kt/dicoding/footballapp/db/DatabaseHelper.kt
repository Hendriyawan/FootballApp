package com.hdev.kt.dicoding.footballapp.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.hdev.kt.dicoding.footballapp.model.Events
import com.hdev.kt.dicoding.footballapp.model.Teams
import org.jetbrains.anko.db.*

class DatabaseHelper(context: Context) : ManagedSQLiteOpenHelper(context, "event_favorite.db", null, 1) {

    companion object {
        private var instance: DatabaseHelper? = null
        @Synchronized
        fun getInstance(context: Context): DatabaseHelper {
            if (instance == null) {
                instance = DatabaseHelper(context.applicationContext)
            }
            return instance as DatabaseHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        //create Table
        db.createTable(
            Events.TABLE_EVENT_FAVORITE, true,
            Events.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            Events.ID_EVENT to TEXT,
            Events.ID_HOME_TEAM to TEXT,
            Events.ID_AWAY_TEAM to TEXT,
            Events.SPORT to TEXT,
            Events.EVENT to TEXT,
            Events.HOME_TEAM to TEXT,
            Events.AWAY_TEAM to TEXT,
            Events.DATE to TEXT,
            Events.TIME to TEXT,
            Events.HOME_SCORE to TEXT,
            Events.AWAY_SCORE to TEXT,
            Events.HOME_GOAL_DETAIL to TEXT,
            Events.AWAY_GOAL_DETAIL to TEXT,
            Events.HOME_RED_CARD to TEXT,
            Events.HOME_YELLOW_CARD to TEXT,
            Events.AWAY_RED_CARD to TEXT,
            Events.AWAY_YELLOW_CARD to TEXT,
            Events.HOME_FORMATION to TEXT,
            Events.AWAY_FORMATION to TEXT,
            Events.HOME_GOAL_KEEPER to TEXT,
            Events.HOME_DEFENSE to TEXT,
            Events.HOME_MIDFIELD to TEXT,
            Events.HOME_FORWARD to TEXT,
            Events.AWAY_GOAL_KEEPER to TEXT,
            Events.AWAY_DEFENSE to TEXT,
            Events.AWAY_MIDFIELD to TEXT,
            Events.AWAY_FORWARD to TEXT
        )

        db.createTable(
            Teams.TABLE_TEAM_FAVORITE, true,
            Teams.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            Teams.ID_TEAM to TEXT,
            Teams.TEAM to TEXT,
            Teams.LEAGUE to TEXT,
            Teams.MANAGER to TEXT,
            Teams.STADIUM to TEXT,
            Teams.STADIUM_THUMB to TEXT,
            Teams.STADIUM_DESCRIPTION to TEXT,
            Teams.STADIUM_LOCATION to TEXT,
            Teams.STADIUM_CAPACITY to TEXT,
            Teams.WEBSITE to TEXT,
            Teams.FACEBOOK to TEXT,
            Teams.TWITTER to TEXT,
            Teams.INSTAGRAM to TEXT,
            Teams.DESCRIPTION_EN to TEXT,
            Teams.TEAM_BADGE to TEXT
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        //here can upgrade tables
        db.dropTable(Events.TABLE_EVENT_FAVORITE, true)
        db.dropTable(Teams.TABLE_TEAM_FAVORITE, true)
    }
}

//Access property for Context
val Context.database: DatabaseHelper
    get() = DatabaseHelper.getInstance(applicationContext)