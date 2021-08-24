package com.dindinn.miniassignment.repo

import com.dindinn.miniassignment.network.ApiClient
import com.dindinn.miniassignment.ui.ingredient.CategoryItemList
import com.dindinn.miniassignment.ui.ingredient.CategoryListResponse
import com.dindinn.miniassignment.ui.order_page.OrderListResponse
import com.google.common.truth.Truth
import org.junit.Test

import retrofit2.Call
import retrofit2.Response

class RepositoryTest {
    val apiClient = ApiClient
    val apiInterface = apiClient.provideApiInterface()

    @Test
    fun callOrderListApi() {
        val call: Call<OrderListResponse> = apiInterface.getOrderList()

        try {
            val response: Response<OrderListResponse> = call.execute()
            val orderResponse: OrderListResponse? = response.body()
            Truth.assertThat(response.isSuccessful && orderResponse!!.status.statusCode == 200)
                .isTrue()
        } catch (e: Exception) {
            e.printStackTrace();

        }


    }

    @Test
    fun callCategoryList() {

        val call: Call<CategoryListResponse> = apiInterface.getCategoryList()

        try {
            val response: Response<CategoryListResponse> = call.execute()
            val orderResponse: CategoryListResponse? = response.body()
            Truth.assertThat(response.isSuccessful && orderResponse!!.status.statusCode == 200)
                .isTrue()
        } catch (e: Exception) {
            e.printStackTrace();

        }

    }

    @Test
    fun callCategoryItemList() {


        val call: Call<CategoryItemList> = apiInterface.getCategoryItems(10)

        try {
            val response: Response<CategoryItemList> = call.execute()
            val orderResponse: CategoryItemList? = response.body()
            Truth.assertThat(response.isSuccessful && orderResponse!!.status.statusCode == 200)
                .isTrue()
        } catch (e: Exception) {
            e.printStackTrace();

        }
    }


    @Test
    fun testCategoryListApiCallFails() {
        val call: Call<CategoryListResponse> = apiInterface.getCategoryList()

        try {
            val response: Response<CategoryListResponse> = call.execute()
            val orderResponse: CategoryListResponse? = response.body()
            Truth.assertThat(!response.isSuccessful && orderResponse!!.status.statusCode != 200)
                .isFalse()
        } catch (e: Exception) {
            e.printStackTrace();

        }


    }


    @Test
    fun testOrderListApiCallFails() {

        val call: Call<OrderListResponse> = apiInterface.getOrderList()

        try {
            val response: Response<OrderListResponse> = call.execute()
            val orderResponse: OrderListResponse? = response.body()
            Truth.assertThat(!response.isSuccessful && orderResponse!!.status.statusCode != 200)
                .isFalse()
        } catch (e: Exception) {
            e.printStackTrace();

        }
    }


}