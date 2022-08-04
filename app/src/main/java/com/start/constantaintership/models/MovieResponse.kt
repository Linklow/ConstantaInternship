package com.start.constantaintership.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieResponse(
    @SerializedName("items")
    val movies: List<MovieModel>
):
    Parcelable {
    constructor(): this(mutableListOf())
}