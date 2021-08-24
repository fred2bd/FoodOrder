package com.dindinn.miniassignment.ui.ingredient

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dindinn.miniassignment.repo.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CategoryItemListViewModel:ViewModel(),Repository.CategoryItemCallBack {
    private val response: MutableLiveData<CategoryItemList> by lazy {
        MutableLiveData<CategoryItemList>()
    }
    private val error: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val responseLivedata: LiveData<CategoryItemList> get() = response
    val errorLivedata: LiveData<String> get() = error



    fun callApi(categoryId:Int,context:Context){
        viewModelScope.launch (Dispatchers.IO){
            Repository.callCategoryItemList(categoryId,context,this@CategoryItemListViewModel)
        }
    }



    override fun onSuccess(data: CategoryItemList) {
        response.value=data
    }

    override fun onError(msg: String) {
        error.value=msg
    }
}