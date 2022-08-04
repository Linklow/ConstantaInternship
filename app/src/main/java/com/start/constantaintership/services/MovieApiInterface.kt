package com.start.constantaintership.services

import com.start.constantaintership.models.MovieResponse
import retrofit2.Call
import retrofit2.http.GET

interface MovieApiInterface {
    @GET("/constanta-android-dev/intership-wellcome-task/main/films.json")
    fun getMovieList(): Call<MovieResponse>
}

// https://raw.githubusercontent.com/constanta-android-dev/intership-wellcome-task/main/films.json