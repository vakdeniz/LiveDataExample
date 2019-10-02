package com.vakdeniz.livedataexample.view

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.vakdeniz.livedataexample.databinding.BlogItemBinding
import com.vakdeniz.livedataexample.model.Blog
import com.vakdeniz.livedataexample.utils.BlogItemLinkListener

class BlogAdapter(private val blogList: List<Blog>) : RecyclerView.Adapter<BlogAdapter.ViewHolder>() {

    private lateinit var listener: BlogItemLinkListener

    override fun getItemCount() = blogList.size

    companion object {

        private lateinit var context: Context

        fun getContext(): Context {
            return context
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        context = parent.context

        val inflater = LayoutInflater.from(parent.context)
        val binding = BlogItemBinding.inflate(inflater)
        listener = BlogItemLinkListener()
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(blogList[position], listener)

    class ViewHolder(
        private var binding: BlogItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(blog: Blog, listener: BlogItemLinkListener) {
            binding.blog = blog
            binding.blogLinkClickListener = listener
            binding.executePendingBindings()
        }
    }
}