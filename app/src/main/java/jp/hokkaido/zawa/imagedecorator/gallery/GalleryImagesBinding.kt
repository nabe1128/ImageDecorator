package jp.hokkaido.zawa.imagedecorator.gallery

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import jp.hokkaido.zawa.imagedecorator.data.ImageData

@BindingAdapter("app:images")
fun setImages(listView: RecyclerView, images: List<ImageData>) {
    (listView.adapter as ImagesAdapter).submitList(images)
}