package jp.hokkaido.zawa.imagedecorator.gallery

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import jp.hokkaido.zawa.imagedecorator.data.ImageData
import jp.hokkaido.zawa.imagedecorator.databinding.ImageItemBinding
import jp.hokkaido.zawa.imagedecorator.utils.getRequestOptions
import jp.hokkaido.zawa.imagedecorator.utils.load

class ImagesAdapter(private val viewModel: GalleryViewModel) : ListAdapter<ImageData, ImagesAdapter.ViewHolder>(ImagesDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        holder.bind(viewModel, item)
        holder.loadImage(item)
    }

    class ViewHolder private constructor(private val binding: ImageItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(viewModel: GalleryViewModel, item: ImageData) {
            binding.viewModel = viewModel
            binding.image = item
            binding.executePendingBindings()
        }

        fun loadImage(item: ImageData) {
            val ctx = binding.itemImage.context
            if (ctx != null) {
                binding.itemImage.load(ctx, item.uri, getRequestOptions(true))
            }
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ImageItemBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }
        }
    }
}

class ImagesDiffCallback : DiffUtil.ItemCallback<ImageData>() {
    override fun areItemsTheSame(oldItem: ImageData, newItem: ImageData): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ImageData, newItem: ImageData): Boolean {
        return oldItem == newItem
    }
}