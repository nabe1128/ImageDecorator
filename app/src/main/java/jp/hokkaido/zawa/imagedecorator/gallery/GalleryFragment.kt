package jp.hokkaido.zawa.imagedecorator.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import jp.hokkaido.zawa.imagedecorator.R
import jp.hokkaido.zawa.imagedecorator.databinding.FragmentGalleryBinding
import jp.hokkaido.zawa.imagedecorator.utils.getViewModelFactory

class GalleryFragment : Fragment() {

    private val viewModel by viewModels<GalleryViewModel> { getViewModelFactory() }

    private lateinit var viewDataBinding: FragmentGalleryBinding

    private lateinit var imagesAdapter: ImagesAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
//         Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_gallery, container, false)
        viewDataBinding = FragmentGalleryBinding.bind(view).apply {
            viewmodel = viewModel
        }
        viewDataBinding.lifecycleOwner = this.viewLifecycleOwner

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupImageListAdapter()

        viewModel.loadImageDataList()
    }

    private fun setupImageListAdapter() {
        val viewModel = viewDataBinding.viewmodel
        if (viewModel != null) {
            imagesAdapter = ImagesAdapter(viewModel)
            viewDataBinding.gallery.adapter = imagesAdapter
        }
    }
}