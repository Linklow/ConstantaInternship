package com.start.constantaintership.models

import com.google.gson.annotations.SerializedName
import java.util.ArrayList

data class MovieResponse(
    @SerializedName("items")
    val movies: ArrayList<MovieModel>?
    )