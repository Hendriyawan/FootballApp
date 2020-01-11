package com.hdev.kt.dicoding.footballapp.mvp.teams

import com.hdev.kt.dicoding.footballapp.api.ApiService
import com.hdev.kt.dicoding.footballapp.model.TeamResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TeamPresenter(private val mainView: TeamView.MainView, private val apiService: ApiService) :
    TeamView.TeamPresenter {
    override fun getTeams(id: String?) {
        mainView.onStartProgress()
        apiService.getTeams(id).enqueue(object : Callback<TeamResponse> {
            override fun onResponse(call: Call<TeamResponse>, response: Response<TeamResponse>) {
                mainView.onStopProgress()
                if (response.isSuccessful && response.code() == 200) {
                    response.body()?.teams?.let {
                        mainView.onTeamLoaded(it)
                    }
                } else {
                    mainView.onFailed(response.message())
                }
            }

            override fun onFailure(call: Call<TeamResponse>, t: Throwable) {
                mainView.onStopProgress()
                mainView.onFailed(t.message)
            }
        })
    }

    override fun getDetailTeam(id: String?) {
        mainView.onStartProgress()
        apiService.getDetailTeam(id).enqueue(object : Callback<TeamResponse> {
            override fun onResponse(call: Call<TeamResponse>, response: Response<TeamResponse>) {
                mainView.onStopProgress()
                if (response.isSuccessful && response.code() == 200) {
                    response.body()?.teams?.let {
                        mainView.onTeamLoaded(it)
                    }
                } else {
                    mainView.onFailed(response.message())
                }
            }

            override fun onFailure(call: Call<TeamResponse>, t: Throwable) {
                mainView.onStopProgress()
                mainView.onFailed(t.message)
            }
        })
    }

    override fun searchTeam(query: String?) {
        mainView.onStartProgress()
        apiService.searchTeam(query).enqueue(object : Callback<TeamResponse> {
            override fun onResponse(call: Call<TeamResponse>, response: Response<TeamResponse>) {
                mainView.onStopProgress()
                if (response.isSuccessful && response.code() == 200) {
                    response.body()?.teams?.let {
                        mainView.onTeamLoaded(it)
                    }
                } else {
                    mainView.onFailed(response.message())
                }
            }

            override fun onFailure(call: Call<TeamResponse>, t: Throwable) {
                mainView.onStopProgress()
                mainView.onFailed(t.message)
            }
        })
    }
}