package com.vakdeniz.livedataexample.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.vakdeniz.livedataexample.model.Blog
import com.vakdeniz.livedataexample.networking.RestApiService
import kotlinx.coroutines.*
import retrofit2.HttpException

class BlogRepository {

    private var movies = mutableListOf<Blog>()
    private var mutableLiveData = MutableLiveData<List<Blog>>()
    val completableJob = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.IO + completableJob)

    fun getMutableLiveData(): MutableLiveData<List<Blog>> {
        coroutineScope.launch {
            val request = RestApiService.init().getPopularBlogAsync()
            withContext(Dispatchers.Main) {
                try {

                    val response = request.await()
                    if (response.blog != null) {
                        movies = response.blog as MutableList<Blog>
                        mutableLiveData.value = movies
                    } else {
                        Log.d("vakdeniz-hata- if-else", "response.blog null")
                    }

                } catch (e: HttpException) {
                    // Log exception //
                    Log.d("vakdeniz-hata - http", e.message())
                } catch (e: Throwable) {
                    // Log error //)
                    Log.d("vakdeniz-hata - try", e.message)

                }

            }
        }
        return mutableLiveData
    }

}