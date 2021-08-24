package com.dindinn.miniassignment.network

import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClientTest {


    @Test
    fun testValidInterface() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://dindinn.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .validateEagerly(true)
            .build()
        retrofit.create(ApiInterface::class.java)
    }


}