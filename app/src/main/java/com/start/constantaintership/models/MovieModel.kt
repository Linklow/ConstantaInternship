package com.start.constantaintership.models

import com.google.gson.annotations.SerializedName


data class MovieModel (

    @SerializedName("title")
    val title: String?,

    @SerializedName("releaseYear")
    val releaseYear: Int?,

    @SerializedName("directorName")
    val directorName: String?,

    @SerializedName("actors")
    var actorsList: List<Actors?>?
)

data class Actors (
    @SerializedName("actorName")
    var actor: String?,
)