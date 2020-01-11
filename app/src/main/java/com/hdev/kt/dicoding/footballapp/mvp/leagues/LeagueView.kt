package com.hdev.kt.dicoding.footballapp.mvp.leagues

import com.hdev.kt.dicoding.footballapp.model.Leagues

class LeagueView {
    interface Presenter {
        fun getDetailLeague(id: String?)
    }

    interface MainView {
        fun onStartProgress()
        fun onStopProgress()
        fun onDetailLoaded(leagues: List<Leagues>)
        fun onFailed(message: String?)
    }
}