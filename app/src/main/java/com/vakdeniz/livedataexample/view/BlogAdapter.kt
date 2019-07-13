package com.vakdeniz.livedataexample.view

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.vakdeniz.livedataexample.R
import com.vakdeniz.livedataexample.model.Blog
import kotlinx.android.synthetic.main.blog_item.view.*

class BlogAdapter(private val blogList: List<Blog>) : RecyclerView.Adapter<BlogAdapter.ViewHolder>() {

    override fun getItemCount() = blogList.size

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        this.context = parent.context

        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.blog_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(blogList[position], context)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(blog: Blog, context: Context) {
            Glide.with(context)
                .load(blog.thumbnail)
                .into(itemView.ivThumbnail)

            itemView.tvTitle.text = blog.title
            itemView.tvDescription.text = blog.description

            itemView.tvLink.setOnClickListener {
                if (!blog.link.isNullOrEmpty()) {
                    val intent = Intent()
                    intent.action = Intent.ACTION_VIEW
                    intent.addCategory(Intent.CATEGORY_BROWSABLE)
                    intent.data = Uri.parse(blog.link)
                    context.startActivity(intent)
                }
            }
        }
    }
}