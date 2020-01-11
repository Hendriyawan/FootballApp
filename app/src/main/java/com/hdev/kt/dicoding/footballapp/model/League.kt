package com.hdev.kt.dicoding.footballapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class League(val id: String, val name: String, val badge: String) : Parcelable