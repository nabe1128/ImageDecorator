package jp.hokkaido.zawa.imagedecorator

import android.Manifest
import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import android.content.pm.PackageManager
import android.os.Build
import android.annotation.TargetApi

@SuppressLint("Registered")
open class BaseActivity : AppCompatActivity() {
    companion object {
        private const val REQUEST_PERMISSION = 10
    }

    @TargetApi(Build.VERSION_CODES.M)
    fun checkPermission() {
        if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestLocationPermission()
        }
    }

    // 許可を求める
    @TargetApi(Build.VERSION_CODES.M)
    private fun requestLocationPermission() {
        if (shouldShowRequestPermissionRationale(
                        Manifest.permission.READ_EXTERNAL_STORAGE)) {
            requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                    REQUEST_PERMISSION)

        } else {
            val toast = Toast.makeText(this,
                    "許可されないとアプリが実行できません", Toast.LENGTH_SHORT)
            toast.show()

            requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                    REQUEST_PERMISSION)

        }
    }

    // 結果の受け取り
    override fun onRequestPermissionsResult(
            requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (requestCode == REQUEST_PERMISSION) {
            if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                // 拒否された場合
                val toast = Toast.makeText(this,
                        "これ以上なにもできません", Toast.LENGTH_SHORT)
                toast.show()
            }
        }
    }
}