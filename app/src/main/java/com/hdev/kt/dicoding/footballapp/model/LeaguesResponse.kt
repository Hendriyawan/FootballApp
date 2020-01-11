package com.hdev.kt.dicoding.footballapp.model

import com.google.gson.annotations.SerializedName

data class LeaguesResponse(
    @SerializedName("leagues")
    val leagues: List<Leagues>
)