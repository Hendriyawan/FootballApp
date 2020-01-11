package com.hdev.kt.dicoding.footballapp.model

import com.google.gson.annotations.SerializedName

data class EventResponse(
    @SerializedName("events")
    val events: List<Events>,

    @SerializedName("event")
    val event : List<Events>
)