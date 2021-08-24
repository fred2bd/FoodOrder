package com.dindinn.miniassignment.ui.ingredient

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dindinn.miniassignment.repo.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CategoryListViewModel : ViewModel(), Repository.CategoryApiCallBack {

    private val response: MutableLiveData<CategoryListResponse> by lazy {
        MutableLiveData<CategoryListResponse>()
    }
    private val error: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val responseLivedata: LiveData<CategoryListResponse> get() = response
    val errorLivedata: LiveData<String> get() = error


    fun callApi(context:Context){
        viewModelScope.launch (Dispatchers.IO){
            Repository.callCategoryList(context,this@CategoryListViewModel)

        }
    }


    override fun onSuccess(data: CategoryListResponse) {

        response.value = data

    }

    override fun onError(msg: String) {
        error.value = msg
    }
}