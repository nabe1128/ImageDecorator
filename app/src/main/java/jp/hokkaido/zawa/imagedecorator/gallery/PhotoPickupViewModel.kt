package jp.hokkaido.zawa.imagedecorator.gallery

import android.app.Application
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import jp.hokkaido.zawa.imagedecorator.data.ImageData
import jp.hokkaido.zawa.imagedecorator.data.ImageDataRepository
import kotlinx.coroutines.launch

class PhotoPickupViewModel(
        application: Application,
        private val imageDataRepository: ImageDataRepository
) : AndroidViewModel(application) {

    private val _imageData = MutableLiveData<ImageData>()
    val imageData: LiveData<ImageData> = _imageData

    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean> = _dataLoading

    fun loadImage(imageId: Long?, draw: (uri: Uri?) -> Unit) {
        _dataLoading.value = true

        viewModelScope.launch {
            if (imageId != null) {
                val imageData = imageDataRepository.fetchImageData(getApplication(), imageId)
                _imageData.value = imageData
            }

            _dataLoading.value = false
            draw(_imageData.value?.uri)
        }
    }
}