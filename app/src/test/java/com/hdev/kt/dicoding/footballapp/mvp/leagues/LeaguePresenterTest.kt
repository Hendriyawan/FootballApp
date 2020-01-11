package com.hdev.kt.dicoding.footballapp.mvp.leagues

import com.hdev.kt.dicoding.footballapp.api.ApiService
import com.hdev.kt.dicoding.footballapp.model.Leagues
import com.hdev.kt.dicoding.footballapp.model.LeaguesResponse
import junit.framework.Assert.assertTrue
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import retrofit2.Response
import retrofit2.mock.Calls

class LeaguePresenterTest {

    @Mock lateinit var api: ApiService
    @Mock lateinit var view: LeagueView.MainView
    private var presenter: LeaguePresenter? = null

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        presenter = LeaguePresenter(view, api)
    }

    @Test
    fun testConnection() {
        val response = ApiService.create().getDetailLeague("4328").execute()
        assertTrue(response.code() == 200)
    }

    @Test
    fun getLeague() {
        val response: MutableList<Leagues> = mutableListOf()
        val leagueResponse = LeaguesResponse(response)
        `when`(api.getDetailLeague("4328")).thenReturn(Calls.response(leagueResponse))
        presenter!!.getDetailLeague("4328")

        verify(view).onStartProgress()
        verify(view).onStopProgress()
        verify(view).onDetailLoaded(response)
    }

    @Test
    fun getLeagueFailed() {

        val content = "{\"leagues\":\"Invalid League ID passed\"}"
        val responseBody = ResponseBody.create(MediaType.parse("application/json"), content)
        `when`(api.getDetailLeague("")).thenReturn(Calls.response(Response.error(400, responseBody)))
        presenter!!.getDetailLeague("")

        verify(view).onStartProgress()
        verify(view).onStopProgress()
        verify(view).onFailed(anyString())
    }
}