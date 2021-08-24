package com.dindinn.miniassignment

import com.dindinn.miniassignment.network.ApiClient
import com.dindinn.miniassignment.ui.order_page.OrderListResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Test
import retrofit2.Call
import retrofit2.Response
import timber.log.Timber

class TestNetwork {
    val apiClient = ApiClient
    val apiInterface = apiClient.provideApiInterface()


    @Test
    fun net() {
        val mockWebServer = MockWebServer()

    }

    fun callApi() {
        apiInterface.getOrderList().enqueue(object : retrofit2.Callback<OrderListResponse> {
            override fun onResponse(call: Call<OrderListResponse>, response: Response<OrderListResponse>) {
                if (response.isSuccessful) {
                    Timber.i("Response from Server::%s", response.body().toString());

                }
            }

            override fun onFailure(call: Call<OrderListResponse>, t: Throwable) {
                Timber.i(t.localizedMessage);
            }
        })

    }


}