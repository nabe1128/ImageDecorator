package jp.hokkaido.zawa.imagedecorator.utils

import android.util.Log
import jp.hokkaido.zawa.imagedecorator.BuildConfig

private const val TAG = "ImageDecorator"

class LogUtil {
    companion object {
        fun info(tag: String, message: String) {
            if (BuildConfig.DEBUG)
                Log.d(tag, message)
        }

        fun info(message: String) {
            info(TAG, message)
        }

        fun debug(tag: String, message: String) {
            if (BuildConfig.DEBUG)
                Log.d(tag, message)
        }

        fun debug(message: String) {
            debug(TAG, message)
        }

        fun error(tag: String, message: String) {
            if (BuildConfig.DEBUG)
                Log.e(tag, message)
        }

        fun error(message: String) {
            error(TAG, message)
        }
    }
}