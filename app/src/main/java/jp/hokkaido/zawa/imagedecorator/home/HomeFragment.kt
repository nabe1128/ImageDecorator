package jp.hokkaido.zawa.imagedecorator.home

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import jp.hokkaido.zawa.imagedecorator.R

class HomeFragment : Fragment() {
    private var listener: OnHandlerHomeFragment? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    fun onButtonPressed() {
        listener?.onGalleryButtonClick()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is OnHandlerHomeFragment) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnHandlerHomeFragment {
        fun onGalleryButtonClick()
    }

    companion object {
        fun newInstance() = HomeFragment()
    }
}
