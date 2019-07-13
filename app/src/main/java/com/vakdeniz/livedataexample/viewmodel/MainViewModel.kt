package com.vakdeniz.livedataexample.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.vakdeniz.livedataexample.model.Blog

class MainViewModel: ViewModel() {

    private val movieRepository = BlogRepository()
    val allBlog: LiveData<List<Blog>> get() = movieRepository.getMutableLiveData()

    override fun onCleared() {
        super.onCleared()
        movieRepository.completableJob.cancel()
    }
}