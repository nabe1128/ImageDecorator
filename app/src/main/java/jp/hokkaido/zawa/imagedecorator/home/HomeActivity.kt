package jp.hokkaido.zawa.imagedecorator.home

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import jp.hokkaido.zawa.imagedecorator.GalleryActivity
import jp.hokkaido.zawa.imagedecorator.R

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_home)
    }

    fun clickGallery(view: View) {
        val intent = Intent(this, GalleryActivity::class.java)
        startActivity(intent)
    }
}