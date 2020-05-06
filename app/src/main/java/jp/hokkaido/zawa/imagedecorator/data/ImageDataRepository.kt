package jp.hokkaido.zawa.imagedecorator.data

import android.content.ContentUris
import android.content.Context
import android.database.Cursor
import android.provider.MediaStore
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ImageDataRepository(
        private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    suspend fun fetchImageData(context: Context?, selectId: Long): ImageData? {
        return withContext(ioDispatcher) {
            val storageUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            val selectionClause = "${MediaStore.Images.ImageColumns._ID} = ?"
            val selectionArgs = arrayOf(selectId.toString())
            val cursor = context?.contentResolver?.query(storageUri,
                    arrayOf(
                            MediaStore.Images.ImageColumns._ID,
                            MediaStore.Images.ImageColumns.BUCKET_ID,
                            MediaStore.Images.ImageColumns.BUCKET_DISPLAY_NAME,
                            MediaStore.Images.ImageColumns.DATE_TAKEN,
                            MediaStore.Images.ImageColumns.DISPLAY_NAME,
                            MediaStore.Images.ImageColumns.MIME_TYPE,
                            MediaStore.Images.ImageColumns.SIZE,
                            MediaStore.Images.ImageColumns.WIDTH,
                            MediaStore.Images.ImageColumns.HEIGHT
                    ),
                    selectionClause, selectionArgs, null)

            if (cursor?.moveToNext() == true) {
                val id = cursor.getLong(0)
                val uri = ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, id)
                ImageData(
                        id = id,
                        categoryId = cursor.getInt(1),
                        categoryName = cursor.getString(2),
                        dateTaken = cursor.getString(3),
                        title = cursor.getString(4),
                        mimeType = cursor.getString(5),
                        size = cursor.getInt(6),
                        width = cursor.getInt(7),
                        height = cursor.getInt(8),
                        uri = uri
                )
            } else {
                null
            }
        }
    }

    suspend fun fetchImageDataAll(context: Context?): List<ImageData> {
        return withContext(ioDispatcher) {
            val storageUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            val cursor = context?.contentResolver?.query(storageUri,
                    arrayOf(
                            MediaStore.Images.ImageColumns._ID,
                            MediaStore.Images.ImageColumns.BUCKET_ID,
                            MediaStore.Images.ImageColumns.BUCKET_DISPLAY_NAME,
                            MediaStore.Images.ImageColumns.DATE_TAKEN,
                            MediaStore.Images.ImageColumns.DISPLAY_NAME,
                            MediaStore.Images.ImageColumns.MIME_TYPE,
                            MediaStore.Images.ImageColumns.SIZE,
                            MediaStore.Images.ImageColumns.WIDTH,
                            MediaStore.Images.ImageColumns.HEIGHT
                    ),
                    null, null, null)

            cursor?.use {
                return@withContext it.map {
                    val id = getLong(0)
                    val uri = ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, id)
                    ImageData(
                            id = id,
                            categoryId = getInt(1),
                            categoryName = getString(2),
                            dateTaken = getString(3),
                            title = getString(4),
                            mimeType = getString(5),
                            size = getInt(6),
                            width = getInt(7),
                            height = getInt(8),
                            uri = uri
                    )
                }
            }

            return@withContext emptyList<ImageData>()
        }
    }
}

private fun <T> Cursor.map(callback: Cursor.() -> T): List<T> {
    val result = mutableListOf<T>()

    while (moveToNext()) {
        result += callback()
    }

    return result
}