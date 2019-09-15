package jp.hokkaido.zawa.imagedecorator

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import jp.hokkaido.zawa.imagedecorator.data.getImageList
import jp.hokkaido.zawa.imagedecorator.utils.glideWithPlaceholder
import kotlinx.android.synthetic.main.activity_gallery.*
import java.io.File

class GalleryActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery)
    }

    override fun onResume() {
        super.onResume()

        val imageData = getImageList(this)
//        imageView.glideWithPlaceholder(this, File(imageData[0].path).toString())
//        imageView.glide(this, File(imageData[0].path).toString())
    }
}

class GalleryAdapter() : RecyclerView.Adapter<GalleryAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.galleryImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}