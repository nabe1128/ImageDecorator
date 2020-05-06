package jp.hokkaido.zawa.imagedecorator.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import jp.hokkaido.zawa.imagedecorator.R
import jp.hokkaido.zawa.imagedecorator.databinding.FragmentPhotoPickupBinding
import jp.hokkaido.zawa.imagedecorator.utils.getViewModelFactory
import jp.hokkaido.zawa.imagedecorator.utils.loadOnFragment
import kotlinx.android.synthetic.main.fragment_photo_pickup.*

class PhotoPickupFragment : Fragment() {

    private val viewModel by viewModels<PhotoPickupViewModel> { getViewModelFactory() }

    private lateinit var viewDataBinding: FragmentPhotoPickupBinding

    private val args: PhotoPickupFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_photo_pickup, container, false)
        viewDataBinding = FragmentPhotoPickupBinding.bind(view).apply {
            viewmodel = viewModel
        }
        viewDataBinding.lifecycleOwner = this.viewLifecycleOwner

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.loadImage(args.photoId) { uri ->
            uri?.let {
                pickupPhoto.loadOnFragment(this, uri)
            }
        }
    }
}