package com.vakdeniz.livedataexample.utils

import android.content.Intent
import android.net.Uri
import com.vakdeniz.livedataexample.view.BlogAdapter

class BlogItemLinkListener {
    fun onLinkClicked(link: String) {
        if (!link.isNullOrEmpty()) {
            val intent = Intent()
            intent.action = Intent.ACTION_VIEW
            intent.addCategory(Intent.CATEGORY_BROWSABLE)
            intent.data = Uri.parse(link)
            BlogAdapter.getContext().startActivity(intent)
        }
    }
}