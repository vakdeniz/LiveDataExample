package com.vakdeniz.livedataexample.view


import android.app.ProgressDialog
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.res.Configuration
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import com.vakdeniz.livedataexample.model.Blog
import com.vakdeniz.livedataexample.viewmodel.MainViewModel
import com.vakdeniz.livedataexample.viewmodel.PopularBlogsResult
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var blogAdapter: BlogAdapter
    private lateinit var progressDialog: ProgressDialog
    private lateinit var alertDialogBuilder: AlertDialog.Builder


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.vakdeniz.livedataexample.R.layout.activity_main)
        progressDialog = ProgressDialog(this)

        alertDialogBuilder = AlertDialog.Builder(this@MainActivity)

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        getPopularBlog()
        swipeRefresh.setOnRefreshListener { getPopularBlog() }

    }

    private fun getPopularBlog() {
        swipeRefresh.isRefreshing = false
        mainViewModel.allMutableBlogs?.observe(this, Observer { result ->
            when (result) {
                is PopularBlogsResult.PopularBlogsSuccess -> {
                    progressDialog.hide()
                    prepareRecyclerView(result.mutableBlogList)
                }
                is PopularBlogsResult.PopularBlogsLoading -> {
                    progressDialog.apply {
                        setMessage("Loading...")
                        show()
                    }
                }
                is PopularBlogsResult.PopularBlogsFail -> {
                    progressDialog.hide()
                    alertDialogBuilder.apply {
                        title = "Warning"
                        setMessage(result.e)
                        create()
                        show()
                    }

                }
            }
        })
    }

    private fun prepareRecyclerView(blogList: List<Blog>) {

        blogAdapter = BlogAdapter(blogList)
        if (this.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            blogRecyclerView.layoutManager = LinearLayoutManager(this)
        } else {
            blogRecyclerView.layoutManager = GridLayoutManager(this, 4)
        }
        blogRecyclerView.itemAnimator = DefaultItemAnimator()
        blogRecyclerView.adapter = blogAdapter

    }
}
