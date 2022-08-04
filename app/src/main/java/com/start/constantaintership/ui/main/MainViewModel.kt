package com.start.constantaintership.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.start.constantaintership.models.MovieModel
import com.start.constantaintership.models.MovieResponse
import com.start.constantaintership.repositories.MovieRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(private val repository: MovieRepository) : ViewModel() {

    lateinit var liveDataList: MutableLiveData<List<MovieModel>>

    init {
        liveDataList = MutableLiveData()
    }

    fun getLiveDataObserver(): MutableLiveData<List<MovieModel>> {
        return liveDataList
    }

    fun makeAPICall() {
        val response = repository.getAllMovies()
        response.enqueue(object: Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                liveDataList.postValue(response.body()?.movies)
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.d("steps",t.message.toString())
            }
        })
    }
}