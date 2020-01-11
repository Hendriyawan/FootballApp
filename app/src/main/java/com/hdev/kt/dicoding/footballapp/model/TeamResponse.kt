package com.hdev.kt.dicoding.footballapp.model

import com.google.gson.annotations.SerializedName

data class TeamResponse(
    @SerializedName("teams")
    val teams: List<Teams>
)