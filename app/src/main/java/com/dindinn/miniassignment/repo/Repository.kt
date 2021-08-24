package com.dindinn.miniassignment.repo

import android.content.Context
import com.dindinn.miniassignment.R
import com.dindinn.miniassignment.network.ApiClient
import com.dindinn.miniassignment.ui.ingredient.CategoryItemList
import com.dindinn.miniassignment.ui.ingredient.CategoryListResponse
import com.dindinn.miniassignment.ui.order_page.OrderListResponse
import retrofit2.Call
import retrofit2.Response
import timber.log.Timber

object Repository {
    private val apiClient = ApiClient
    private val apiInterface = apiClient.provideApiInterface()

    fun callOrderListApi(context: Context, callBack: OrderApiCallBack) {
        apiInterface.getOrderList().enqueue(
            object : retrofit2.Callback<OrderListResponse> {
                override fun onResponse(call: Call<OrderListResponse>, response: Response<OrderListResponse>) {
                    if (response.isSuccessful) {
                        Timber.i("Response from Server::%s", response.body().toString())
                        response.body()?.let { callBack.onSuccess(it) }


                    } else {
                        callBack.onError(context.getString(R.string.error_msg))
                    }
                }

                override fun onFailure(call: Call<OrderListResponse>, t: Throwable) {
                    Timber.i(t.localizedMessage)
                    callBack.onError(t.localizedMessage!!)

                }
            })

    }


    interface OrderApiCallBack {
        fun onSuccess(data: OrderListResponse)
        fun onError(msg: String)
    }





    fun callCategoryList(context: Context, callBack: CategoryApiCallBack) {
        apiInterface.getCategoryList().enqueue(
            object : retrofit2.Callback<CategoryListResponse> {
                override fun onResponse(call: Call<CategoryListResponse>, response: Response<CategoryListResponse>) {
                    if (response.isSuccessful) {
                        Timber.i("Response from Server::%s", response.body().toString())
                        response.body()?.let { callBack.onSuccess(it) }


                    } else {
                        callBack.onError(context.getString(R.string.error_msg))
                    }
                }

                override fun onFailure(call: Call<CategoryListResponse>, t: Throwable) {
                    Timber.i(t.localizedMessage)
                    callBack.onError(t.localizedMessage!!)

                }
            })

    }


    interface CategoryApiCallBack {
        fun onSuccess(data: CategoryListResponse)
        fun onError(msg: String)
    }





    fun callCategoryItemList(category_id:Int,context: Context, callBack: CategoryItemCallBack) {
        apiInterface.getCategoryItems(category_id).enqueue(
            object : retrofit2.Callback<CategoryItemList> {
                override fun onResponse(call: Call<CategoryItemList>, response: Response<CategoryItemList>) {
                    if (response.isSuccessful) {
                        Timber.i("Response from Server::%s", response.body().toString())
                        response.body()?.let { callBack.onSuccess(it) }


                    } else {
                        callBack.onError(context.getString(R.string.error_msg))
                    }
                }

                override fun onFailure(call: Call<CategoryItemList>, t: Throwable) {
                    Timber.i(t.localizedMessage)
                    callBack.onError(t.localizedMessage!!)

                }
            })

    }


    interface CategoryItemCallBack {
        fun onSuccess(data: CategoryItemList)
        fun onError(msg: String)
    }







}