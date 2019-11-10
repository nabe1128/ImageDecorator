package jp.hokkaido.zawa.imagedecorator.gallery

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import jp.hokkaido.zawa.imagedecorator.data.ImageData
import jp.hokkaido.zawa.imagedecorator.data.ImageDataRepository
import kotlinx.coroutines.launch

class GalleryViewModel(
        application: Application,
        private val imageDataRepository: ImageDataRepository
) : AndroidViewModel(application) {

    private val _imageDataList = MutableLiveData<List<ImageData>>().apply { value = emptyList() }
    val imageDataList: LiveData<List<ImageData>> = _imageDataList

    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean> = _dataLoading

    init {
        loadImageDataList()
    }

    fun loadImageDataList() {
        _dataLoading.value = true

        viewModelScope.launch {
            val imageDataList = imageDataRepository.getImageDatas(context = getApplication())

            val imageDataListToShow = mutableListOf<ImageData>()
            imageDataList.forEach { imageData ->
                // TODO フォルダーのフィルタリング
                imageDataListToShow.add(imageData)
            }

            _imageDataList.value = ArrayList(imageDataListToShow)

            _dataLoading.value = false
        }
    }
}