package jp.hokkaido.zawa.imagedecorator.data

import android.content.Context
import android.database.Cursor
import android.provider.MediaStore

data class ImageData(
        val categoryId: Int,
        val categoryName: String,
        val thumbnailId: Int,
        val dateTaken: String,
        val path: String,
        val title: String,
        val mimeType: String,
        val size: Int,
        val width: Int,
        val height: Int
)

private fun <T> Cursor.map(callback: Cursor.() -> T): List<T> {
    val result = mutableListOf<T>()

    while (moveToNext()) {
        result += callback()
    }

    return result
}

fun getImageList(context: Context): List<ImageData> {
    val cursor = context.contentResolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            arrayOf(
                    MediaStore.Images.ImageColumns.BUCKET_ID,
                    MediaStore.Images.ImageColumns.BUCKET_DISPLAY_NAME,
                    MediaStore.Images.ImageColumns.MINI_THUMB_MAGIC,
                    MediaStore.Images.ImageColumns.DATE_TAKEN,
                    MediaStore.Images.ImageColumns.DATA,
                    MediaStore.Images.ImageColumns.DISPLAY_NAME,
                    MediaStore.Images.ImageColumns.MIME_TYPE,
                    MediaStore.Images.ImageColumns.SIZE,
                    MediaStore.Images.ImageColumns.WIDTH,
                    MediaStore.Images.ImageColumns.HEIGHT
            ),
            null, null, null)

    cursor?.use {
        return cursor.map {
            ImageData(
                    categoryId = getInt(0),
                    categoryName = getString(1),
                    thumbnailId = getInt(2),
                    dateTaken = getString(3),
                    path = getString(4),
                    title = getString(5),
                    mimeType = getString(6),
                    size = getInt(7),
                    width = getInt(8),
                    height = getInt(9)
            )
        }
    }

    return emptyList()
}