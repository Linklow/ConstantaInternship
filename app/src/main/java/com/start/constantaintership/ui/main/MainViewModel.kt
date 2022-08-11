package com.start.constantaintership.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.start.constantaintership.models.MovieModel
import com.start.constantaintership.models.MovieResponse
import com.start.constantaintership.repositories.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(private val repository: MovieRepository) : ViewModel() {

    var liveDataList: MutableLiveData<List<MovieModel>> = MutableLiveData()

    fun getLiveDataObserver(): MutableLiveData<List<MovieModel>> {
        return liveDataList
    }

    fun makeAPICall() {
        val response = repository.getAllMovies()
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                    response.enqueue(object: Callback<MovieResponse> {
                        override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                            liveDataList.postValue(response.body()?.movies!!)
                        }
                        override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                            Log.d("failure on response",t.message.toString())
                        }
                    })
            }
        }
    }
}