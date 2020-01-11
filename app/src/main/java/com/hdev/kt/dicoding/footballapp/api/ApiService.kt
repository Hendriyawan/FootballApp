package com.hdev.kt.dicoding.footballapp.api

import com.hdev.kt.dicoding.footballapp.BuildConfig
import com.hdev.kt.dicoding.footballapp.model.EventResponse
import com.hdev.kt.dicoding.footballapp.model.LeaguesResponse
import com.hdev.kt.dicoding.footballapp.model.TableResponse
import com.hdev.kt.dicoding.footballapp.model.TeamResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface ApiService {

    companion object {
        fun create(): ApiService {
            val client = OkHttpClient().newBuilder().addInterceptor(HttpLoggingInterceptor().apply {
                level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
            })
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS).build()


            return Retrofit.Builder().apply {
                baseUrl("https://www.thesportsdb.com")
                client(client)
                addConverterFactory(GsonConverterFactory.create())

            }.build().create(ApiService::class.java)
        }
    }

    @GET("api/v1/json/1/lookupleague.php")
    fun getDetailLeague(@Query("id") id: String?): Call<LeaguesResponse>

    @GET("api/v1/json/1/eventspastleague.php")
    fun getPreviousMatch(@Query("id") id: String?): Call<EventResponse>

    @GET("api/v1/json/1/eventsnextleague.php")
    fun getNextMatch(@Query("id") id: String?): Call<EventResponse>

    @GET("api/v1/json/1/lookupevent.php")
    fun getDetailEvent(@Query("id") id: String?): Call<EventResponse>

    @GET("api/v1/json/1/searchevents.php")
    fun getEventBySearch(@Query("e") query: String?): Call<EventResponse>

    @GET("api/v1/json/1/lookuptable.php")
    fun getTable(@Query("l") l: String?): Call<TableResponse>

    @GET("api/v1/json/1/lookup_all_teams.php")
    fun getTeams(@Query("id") id: String?): Call<TeamResponse>

    @GET("api/v1/json/1/lookupteam.php")
    fun getDetailTeam(@Query("id") id: String?): Call<TeamResponse>

    @GET("api/v1/json/1/searchteams.php")
    fun searchTeam(@Query("t") query: String?): Call<TeamResponse>

}