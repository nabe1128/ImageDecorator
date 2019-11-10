package jp.hokkaido.zawa.imagedecorator.utils

import androidx.fragment.app.Fragment
import jp.hokkaido.zawa.imagedecorator.GalleryApplication
import jp.hokkaido.zawa.imagedecorator.ViewModelFactory

fun Fragment.getViewModelFactory(): ViewModelFactory {
    val app = requireContext().applicationContext as GalleryApplication
    val repository = app.imageDataRepository
    return ViewModelFactory(app, repository)
}