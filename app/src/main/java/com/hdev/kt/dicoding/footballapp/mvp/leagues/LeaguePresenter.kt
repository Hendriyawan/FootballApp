package com.hdev.kt.dicoding.footballapp.mvp.leagues

import com.hdev.kt.dicoding.footballapp.api.ApiService
import com.hdev.kt.dicoding.footballapp.model.LeaguesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LeaguePresenter(private val mainView: LeagueView.MainView, private val apiService: ApiService) :
    LeagueView.Presenter {
    /** get detail of league
     * @param id id League
     */
    override fun getDetailLeague(id: String?) {
        mainView.onStartProgress()
        apiService.getDetailLeague(id).enqueue(object : Callback<LeaguesResponse> {
            override fun onResponse(call: Call<LeaguesResponse>, response: Response<LeaguesResponse>) {
                mainView.onStopProgress()
                if (response.isSuccessful && response.code() == 200) {
                    response.body()?.leagues?.let {
                        mainView.onDetailLoaded(it)
                    }
                } else {
                    mainView.onFailed(response.message())
                }
            }

            override fun onFailure(call: Call<LeaguesResponse>, t: Throwable) {
                mainView.onStopProgress()
                mainView.onFailed(t.message)
            }
        })
    }
}