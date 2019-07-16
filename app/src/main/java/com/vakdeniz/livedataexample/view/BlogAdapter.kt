package com.vakdeniz.livedataexample.view

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.vakdeniz.livedataexample.databinding.BlogItemBinding
import com.vakdeniz.livedataexample.model.Blog
import com.vakdeniz.livedataexample.utils.BlogItemLinkListener
import kotlinx.android.synthetic.main.blog_item.view.*

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
        return ViewHolder(binding, context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(blogList[position], listener)

    class ViewHolder(
        private var binding: BlogItemBinding,
        private val context: Context
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(blog: Blog, listener: BlogItemLinkListener) {
            binding.blog = blog
            binding.blogLinkClickListener = listener
            binding.executePendingBindings()

            Glide.with(context)
                .load(binding.blog?.thumbnail)
                .into(itemView.ivThumbnail)

        }
    }

}