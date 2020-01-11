package com.hdev.kt.dicoding.footballapp.mvp.events

import com.hdev.kt.dicoding.footballapp.model.Events
import com.hdev.kt.dicoding.footballapp.model.Teams

class EventView {
    interface Presenter {
        fun getDetailEvent(id: String?)
        fun getPreviousMatch(id: String?)
        fun searchEvent(query : String?)
        fun getNextMatch(id: String?)
        fun getHomeDetail(id: String?)
        fun getAwayDetail(id: String?)
    }

    interface MainView {
        fun onStartProgress()
        fun onStopProgress()
        fun onEventLoaded(events: List<Events>)
        fun onHomeLoaded(home: List<Teams>)
        fun onAwayLoaded(away: List<Teams>)
        fun onFailed(message: String?)
    }
}