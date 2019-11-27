package jp.hokkaido.zawa.imagedecorator.utils

import android.content.Context
import android.net.Uri
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

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

fun ImageView.load(context: Context, url: Uri, options: RequestOptions) {
    Glide.with(context)
            .setDefaultRequestOptions(options)
            .load(url)
            .into(this)
}

fun ImageView.loadOnFragment(fragment: Fragment, uri: Uri) {
    Glide.with(fragment)
            .load(uri)
            .into(this)
}

fun getRequestOptions(isCrop: Boolean = true): RequestOptions {
    val requestOptions = RequestOptions()

    if (isCrop) requestOptions.centerCrop()

    return requestOptions
}