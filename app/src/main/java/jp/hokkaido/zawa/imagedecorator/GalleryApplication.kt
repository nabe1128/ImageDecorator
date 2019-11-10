package jp.hokkaido.zawa.imagedecorator

import android.app.Application
import jp.hokkaido.zawa.imagedecorator.data.ImageDataRepository

class GalleryApplication : Application() {

    val imageDataRepository: ImageDataRepository = ImageDataRepository()
}