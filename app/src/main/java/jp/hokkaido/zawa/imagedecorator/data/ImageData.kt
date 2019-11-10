package jp.hokkaido.zawa.imagedecorator.data

import android.net.Uri

data class ImageData(
        val id: Long,
        val categoryId: Int,
        val categoryName: String,
        val dateTaken: String?,
        val title: String,
        val mimeType: String,
        val size: Int,
        val width: Int,
        val height: Int,
        val uri: Uri
)