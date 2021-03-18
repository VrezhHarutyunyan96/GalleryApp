package com.android.helix.task.ui.details.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.helix.task.base.Event

class DetailsViewModel : ViewModel() {

    val photoButtonClickEvent = MutableLiveData<Event<Unit>>()
    val videoButtonClickEvent = MutableLiveData<Event<Unit>>()

    fun onPhotoClick() {
        photoButtonClickEvent.value = Event(Unit)
    }

    fun onVideoClick() {
        videoButtonClickEvent.value = Event(Unit)
    }
}