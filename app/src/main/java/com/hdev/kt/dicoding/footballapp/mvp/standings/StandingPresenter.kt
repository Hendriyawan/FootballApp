package com.hdev.kt.dicoding.footballapp.mvp.standings

import com.hdev.kt.dicoding.footballapp.api.ApiService
import com.hdev.kt.dicoding.footballapp.model.TableResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StandingPresenter(private val mainView: StandingView.MainView, private val apiService: ApiService) :
    StandingView.StandingPresenter {
    override fun getTable(id: String?) {
        mainView.onStartProgress()
        apiService.getTable(id).enqueue(object : Callback<TableResponse> {
            override fun onResponse(call: Call<TableResponse>, response: Response<TableResponse>) {
                mainView.onStopProgress()
                if (response.isSuccessful && response.code() == 200) {
                    response.body()?.tables?.let {
                        mainView.onTableLoaded(it)
                    }
                } else {
                    mainView.onFailed(response.message())
                }
            }

            override fun onFailure(call: Call<TableResponse>, t: Throwable) {
                mainView.onStopProgress()
                mainView.onFailed(t.message)
            }
        })
    }
}