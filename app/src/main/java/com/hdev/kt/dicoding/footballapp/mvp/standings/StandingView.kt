package com.hdev.kt.dicoding.footballapp.mvp.standings

import com.hdev.kt.dicoding.footballapp.model.Tables

class StandingView {
    interface StandingPresenter {
        fun getTable(id: String?)
    }

    interface MainView {
        fun onStartProgress()
        fun onStopProgress()
        fun onTableLoaded(tables: List<Tables>)
        fun onFailed(message: String?)
    }
}