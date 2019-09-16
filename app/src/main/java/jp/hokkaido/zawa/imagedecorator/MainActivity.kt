package jp.hokkaido.zawa.imagedecorator

import android.os.Bundle
import jp.hokkaido.zawa.imagedecorator.home.HomeFragment

class MainActivity : BaseActivity(), HomeFragment.OnHandlerHomeFragment {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkPermission()
        setContentView(R.layout.activity_main)
    }

    override fun onGalleryButtonClick() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
