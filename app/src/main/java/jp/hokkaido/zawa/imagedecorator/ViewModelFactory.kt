package jp.hokkaido.zawa.imagedecorator

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import jp.hokkaido.zawa.imagedecorator.data.ImageDataRepository
import jp.hokkaido.zawa.imagedecorator.gallery.GalleryViewModel
import jp.hokkaido.zawa.imagedecorator.gallery.PhotoPickupViewModel

class ViewModelFactory(
        private val galleryApplication: GalleryApplication,
        private val imageDataRepository: ImageDataRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            with(modelClass) {
                when {
                    isAssignableFrom(GalleryViewModel::class.java) ->
                        GalleryViewModel(galleryApplication, imageDataRepository)
                    isAssignableFrom(PhotoPickupViewModel::class.java) ->
                        PhotoPickupViewModel(galleryApplication, imageDataRepository)
                    else ->
                        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
                }
            } as T
}