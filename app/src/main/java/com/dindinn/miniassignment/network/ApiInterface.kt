package com.dindinn.miniassignment.network

import com.dindinn.miniassignment.ui.ingredient.CategoryItemList
import com.dindinn.miniassignment.ui.ingredient.CategoryListResponse
import com.dindinn.miniassignment.ui.order_page.OrderListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("order_list")
    fun getOrderList(
    ): Call<OrderListResponse>

    @GET("category")
    fun getCategoryList(
    ): Call<CategoryListResponse>

    @GET("ingredient_by_category?")
    fun getCategoryItems(
        @Query("category_id") id: Int
    ): Call<CategoryItemList>

}