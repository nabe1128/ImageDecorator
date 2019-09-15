package jp.hokkaido.zawa.imagedecorator.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import jp.hokkaido.zawa.imagedecorator.R

fun ImageView.glideWithPlaceholder(context: Context, url: String) {
    val requestOptions = RequestOptions().apply {
//        placeholder(R.drawable.ic_placeholder)
//        error(R.drawable.ic_error)
        fitCenter()
        dontAnimate()
    }

    Glide.with(context)
            .setDefaultRequestOptions(requestOptions)
            .load(url)
            .into(this)
}

fun ImageView.load(context: Context, url: String) {
    Glide.with(context)
            .load(url)
            .into(this)
}