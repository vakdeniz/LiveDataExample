package com.vakdeniz.livedataexample.viewmodel

import androidx.lifecycle.MutableLiveData
import com.vakdeniz.livedataexample.model.Blog
import com.vakdeniz.livedataexample.model.BlogWrapper
import kotlinx.coroutines.*
import retrofit2.HttpException

class BlogRepository(private val restApiService: Deferred<BlogWrapper>) {

    var popularBlogsMutableLiveData = MutableLiveData<PopularBlogsResult>()
    val completableJob = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.Main + completableJob)

    fun getPopularBlogsCoroutineCall() {
        coroutineScope.launch {
            popularBlogsMutableLiveData.value = PopularBlogsResult.PopularBlogsLoading()
            try {
                val request = restApiService
                withContext(Dispatchers.Main) {
                    popularBlogsMutableLiveData.value =
                        request.await().blog?.let {
                            PopularBlogsResult.PopularBlogsSuccess(it)
                        }
                }
            } catch (e: Throwable) {
                popularBlogsMutableLiveData.value.apply {
                    PopularBlogsResult.PopularBlogsFail(e.localizedMessage)
                }
            } catch (e: HttpException) {
                popularBlogsMutableLiveData.value.apply {
                    PopularBlogsResult.PopularBlogsFail(e.message())
                }
            }
        }
    }

}


sealed class PopularBlogsResult {
    data class PopularBlogsSuccess(val mutableBlogList: MutableList<Blog>) : PopularBlogsResult()
    data class PopularBlogsFail(val e: String) : PopularBlogsResult()
    class PopularBlogsLoading : PopularBlogsResult()
}
