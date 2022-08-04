package com.start.constantaintership.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieModel (

    @SerializedName("title")
    val title: String?,

    @SerializedName("releaseYear")
    val releaseYear: Int?,

    @SerializedName("directorName")
    val directorName: String?,

    @SerializedName("actors")
    var actorsList: List<Actors?>?

): Parcelable {
    constructor() : this("",0,"", null)
}

@Parcelize
data class Actors (
    @SerializedName("actorName")
    var actor: String?,
) : Parcelable {
    constructor() : this(null)
}