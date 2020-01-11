package com.hdev.kt.dicoding.footballapp.mvp.teams

import com.hdev.kt.dicoding.footballapp.model.Teams

class TeamView {
    interface TeamPresenter {
        fun getTeams(id: String?)
        fun getDetailTeam(id : String?)
        fun searchTeam(query : String?)
    }

    interface MainView {
        fun onStartProgress()
        fun onStopProgress()
        fun onTeamLoaded(teams: List<Teams>)
        fun onFailed(message: String?)
    }
}