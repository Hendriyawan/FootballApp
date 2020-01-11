package com.hdev.kt.dicoding.footballapp.mvp.events

import com.hdev.kt.dicoding.footballapp.api.ApiService
import com.hdev.kt.dicoding.footballapp.model.EventResponse
import com.hdev.kt.dicoding.footballapp.model.Events
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

class EventPresenterTest {

    @Mock
    lateinit var api: ApiService
    @Mock
    lateinit var view: EventView.MainView
    private var presenter: EventPresenter? = null

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        presenter = EventPresenter(view, api)
    }

    @Test
    fun testConnection() {
        val lastEventResponse = ApiService.create().getPreviousMatch("4328").execute()
        val nextEventResponse = ApiService.create().getNextMatch("4328").execute()

        assertTrue(lastEventResponse.code() == 200)
        assertTrue(nextEventResponse.code() == 200)
    }

    @Test
    fun getPreviousMatch() {
        val response: MutableList<Events> = mutableListOf()
        val eventResponse = EventResponse(response, response)
        `when`(api.getPreviousMatch("4328")).thenReturn(Calls.response(eventResponse))
        presenter!!.getPreviousMatch("4328")

        verify(view).onStartProgress()
        verify(view).onStopProgress()
        verify(view).onEventLoaded(response)
    }

    @Test
    fun getPreviousMatchFailed() {
        val content = "{\"leagues\":\"Invalid League ID passed\"}"
        val responseBody = ResponseBody.create(MediaType.parse("application/json"), content)
        `when`(api.getPreviousMatch("")).thenReturn(Calls.response(Response.error(400, responseBody)))
        presenter!!.getPreviousMatch("")

        verify(view).onStartProgress()
        verify(view).onStopProgress()
        verify(view).onFailed(anyString())
    }

    @Test
    fun getNextMatch() {
        val response: MutableList<Events> = mutableListOf()
        val eventResponse = EventResponse(response, response)
        `when`(api.getNextMatch("4328")).thenReturn(Calls.response(eventResponse))
        presenter!!.getNextMatch("4328")

        verify(view).onStartProgress()
        verify(view).onStopProgress()
        verify(view).onEventLoaded(response)
    }

    @Test
    fun getNextMatchFailed() {
        val content = "{\"leagues\":\"Invalid League ID passed\"}"
        val responseBody = ResponseBody.create(MediaType.parse("application/json"), content)
        `when`(api.getNextMatch("")).thenReturn(Calls.response(Response.error(400, responseBody)))
        presenter!!.getNextMatch("")

        verify(view).onStartProgress()
        verify(view).onStopProgress()
        verify(view).onFailed(anyString())
    }

    @Test
    fun testGetDetailEvent() {
        val response: MutableList<Events> = mutableListOf()
        val detailEventResponse = EventResponse(response, response)
        `when`(api.getDetailEvent("602318")).thenReturn(Calls.response(detailEventResponse))
        presenter!!.getDetailEvent("602318")

        verify(view).onStartProgress()
        verify(view).onStopProgress()
        verify(view).onEventLoaded(response)

    }

    @Test
    fun testGetDetailEventFailed() {
        val content = "{\"leagues\":\"Invalid League ID passed\"}"
        val responseBody = ResponseBody.create(MediaType.parse("application/json"), content)
        `when`(api.getDetailEvent("")).thenReturn(Calls.response(Response.error(400, responseBody)))
        presenter!!.getDetailEvent("")

        verify(view).onStartProgress()
        verify(view).onStopProgress()
        verify(view).onFailed(anyString())
    }

    @Test
    fun getDetailEvent() {
        val event = mutableListOf(
            Events(
                null,
                idEvent = "602318",
                idHomeTeam = "133612",
                idAwayTeam = "134777",
                strSport = "Soccer",
                strEvent = "Man United vs Newcastle",
                strHomeTeam = "Man United",
                strAwayTeam = "Newcastle",
                dateEvent = "2019-12-26",
                strTime = "17:30:00",
                homeScore = "4",
                awayScore = "1",
                homeGoalDetail = "",
                awayGoalDetail = "",
                homeRedCards = "",
                homeYellowCards = "",
                awayRedCards = "",
                awayYellowCards = "",
                homeFormation = null,
                awayFormation = null,
                homeGoalKeeper = "",
                homeDefense = "",
                homeMidfield = "",
                homeForward = "",
                awayKeeper = "",
                awayDefense = "",
                awayMidfield = "",
                awayForward = ""
            )
        )
        val response = ApiService.create().getDetailEvent("602318").execute()
        val data = response.body()
        assert(event == data?.events)

    }

    @Test
    fun getSearchEvent() {
        val response: MutableList<Events> = mutableListOf()
        val searchEventResponse = EventResponse(response, response)
        `when`(api.getEventBySearch("Arsenal")).thenReturn(Calls.response(searchEventResponse))
        presenter!!.searchEvent("Arsenal")

        verify(view).onStartProgress()
        verify(view).onStopProgress()
        verify(view).onEventLoaded(response)
    }

    @Test
    fun testSearchEventNotEmpty() {
        val searchResult: MutableList<Events> = mutableListOf()
        val response = ApiService.create().getEventBySearch("Arsenal").execute()
        val data = response.body()?.event
        data?.let {
            searchResult.clear()
            searchResult.addAll(it.filter { s -> s.strSport == "Soccer" })
        }
        assertTrue(searchResult.isNotEmpty())

    }

    @Test
    fun testSearchEventEmpty() {
        val searchResult: MutableList<Events> = mutableListOf()
        val response = ApiService.create().getEventBySearch("Indonesia").execute()
        val data = response.body()?.event
        data?.let {
            searchResult.clear()
            searchResult.addAll(it.filter { s -> s.strSport == "Soccer" })
        }
        assertTrue(searchResult.isNullOrEmpty())
    }
}