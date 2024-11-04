package com.example.dogapp
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Dogs(
    val titleImage: Int,
    var name: String,
    var breed: String,
    var age: Int,
    var weight: Int,
    var activity: String
) : Parcelable