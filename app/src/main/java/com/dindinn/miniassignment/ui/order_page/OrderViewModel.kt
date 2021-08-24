package com.dindinn.miniassignment.ui.order_page

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dindinn.miniassignment.repo.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class OrderViewModel : ViewModel(), Repository.OrderApiCallBack {

    private val response: MutableLiveData<OrderListResponse> by lazy {
        MutableLiveData<OrderListResponse>()
    }
    private val error: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val responseLivedata: LiveData<OrderListResponse> get() = response
    val errorLivedata: LiveData<String> get() = error


    fun callOrderApi(context: Context) {

        if (response.value == null) {

            viewModelScope.launch(Dispatchers.IO) {
                Repository.callOrderListApi(context, this@OrderViewModel)


            }
        }
    }


    override fun onSuccess(data: OrderListResponse) {
        response.value = data
    }

    override fun onError(msg: String) {
        error.value = msg
    }


}