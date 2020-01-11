package com.hdev.kt.dicoding.footballapp.mvp.events

import com.hdev.kt.dicoding.footballapp.api.ApiService
import com.hdev.kt.dicoding.footballapp.model.EventResponse
import com.hdev.kt.dicoding.footballapp.model.Events
import com.hdev.kt.dicoding.footballapp.model.TeamResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EventPresenter(private val mainView: EventView.MainView, private val apiService: ApiService) :
    EventView.Presenter {
    /** get previous matches
     * @param id Id league
     */
    override fun getPreviousMatch(id: String?) {
        mainView.onStartProgress()
        apiService.getPreviousMatch(id).enqueue(object : Callback<EventResponse> {
            override fun onResponse(call: Call<EventResponse>, response: Response<EventResponse>) {
                mainView.onStopProgress()
                if (response.isSuccessful && response.code() == 200) {
                    response.body()?.events?.let {
                        mainView.onEventLoaded(it)
                    }
                } else {
                    mainView.onFailed(response.message())
                }
            }

            override fun onFailure(call: Call<EventResponse>, t: Throwable) {
                mainView.onStopProgress()
                mainView.onFailed(t.message)
            }
        })
    }

    /** get next matches
     * @param id Id league
     */
    override fun getNextMatch(id: String?) {
        mainView.onStartProgress()
        apiService.getNextMatch(id).enqueue(object : Callback<EventResponse> {
            override fun onResponse(call: Call<EventResponse>, response: Response<EventResponse>) {
                mainView.onStopProgress()
                if (response.isSuccessful && response.code() == 200) {
                    response.body()?.events?.let {
                        mainView.onEventLoaded(it)
                    }
                } else {
                    mainView.onFailed(response.message())
                }
            }

            override fun onFailure(call: Call<EventResponse>, t: Throwable) {
                mainView.onStopProgress()
                mainView.onFailed(t.message)
            }
        })
    }

    override fun getDetailEvent(id: String?) {
        mainView.onStartProgress()
        apiService.getDetailEvent(id).enqueue(object : Callback<EventResponse> {
            override fun onResponse(call: Call<EventResponse>, response: Response<EventResponse>) {
                mainView.onStopProgress()
                if (response.isSuccessful && response.code() == 200) {
                    response.body()?.events?.let {
                        mainView.onEventLoaded(it)
                    }
                } else {
                    mainView.onFailed(response.message())
                }
            }

            override fun onFailure(call: Call<EventResponse>, t: Throwable) {
                mainView.onStopProgress()
                mainView.onFailed(t.message)
            }
        })
    }

    override fun getHomeDetail(id: String?) {
        mainView.onStartProgress()
        apiService.getDetailTeam(id).enqueue(object : Callback<TeamResponse> {
            override fun onResponse(call: Call<TeamResponse>, response: Response<TeamResponse>) {
                mainView.onStopProgress()
                if (response.isSuccessful && response.code() == 200) {
                    response.body()?.teams?.let {
                        mainView.onHomeLoaded(it)
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

    override fun getAwayDetail(id: String?) {
        mainView.onStartProgress()
        apiService.getDetailTeam(id).enqueue(object : Callback<TeamResponse> {
            override fun onResponse(call: Call<TeamResponse>, response: Response<TeamResponse>) {
                mainView.onStopProgress()
                if (response.isSuccessful && response.code() == 200) {
                    response.body()?.teams?.let {
                        mainView.onAwayLoaded(it)
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

    override fun searchEvent(query: String?) {
        val eventSearch: MutableList<Events> = mutableListOf()
        mainView.onStartProgress()
        apiService.getEventBySearch(query).enqueue(object : Callback<EventResponse> {
            override fun onResponse(call: Call<EventResponse>, response: Response<EventResponse>) {
                mainView.onStopProgress()
                if (response.isSuccessful && response.code() == 200) {
                    response.body()?.event?.let {
                        val eventFilter: List<Events> = it.filter { s -> s.strSport == "Soccer" }
                        eventSearch.clear()
                        eventSearch.addAll(eventFilter)
                        mainView.onEventLoaded(eventSearch)
                    }
                } else {
                    mainView.onFailed(response.message())
                }
            }

            override fun onFailure(call: Call<EventResponse>, t: Throwable) {
                mainView.onStopProgress()
                mainView.onFailed(t.message)
            }
        })
    }
}