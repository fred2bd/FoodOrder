package com.dindinn.miniassignment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FragmentToActivityCommunicationViewModel : ViewModel() {

    private val playAlarm: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    val playAlarmLiveData: LiveData<Boolean> get() = playAlarm
    private val showProgressbar: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    val showProgressbarLiveData: LiveData<Boolean> get() = showProgressbar


    fun play(isPlay: Boolean) {
        playAlarm.value = isPlay
    }


    fun showProgressBar(show: Boolean) {
        showProgressbar.value = show

    }


}