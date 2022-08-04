package com.start.constantaintership.repositories

import com.start.constantaintership.models.MovieResponse
import com.start.constantaintership.services.MovieApiInterface
import com.start.constantaintership.services.MovieApiService
import retrofit2.Call

class MovieRepository{

    fun getAllMovies(): Call<MovieResponse> {
        val retroInstance = MovieApiService.getInstance()
        val retroService = retroInstance.create(MovieApiInterface::class.java)
        return retroService.getMovieList()
    }
}