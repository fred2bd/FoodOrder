package com.dindinn.miniassignment.ui.order_page


import com.google.gson.annotations.SerializedName

data class OrderListResponse(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("status")
    val status: Status
) {
    data class Data(
        @SerializedName("addon")
        val addon: List<Addon>,
        @SerializedName("alerted_at")
        val alertedAt: String, // 2021-06-10T15:03+00Z
        @SerializedName("created_at")
        val createdAt: String, // 2021-06-10T15:00+00Z
        @SerializedName("expired_at")
        val expiredAt: String, // 2021-06-10T15:05+00Z
        @SerializedName("id")
        val id: Int, // 10
        @SerializedName("quantity")
        val quantity: Int, // 1
        @SerializedName("title")
        val title: String // Special extra large fried rice
    ) {
        data class Addon(
            @SerializedName("id")
            val id: Int, // 21
            @SerializedName("quantity")
            val quantity: Int, // 3
            @SerializedName("title")
            val title: String // Fried Egg
        )
    }

    data class Status(
        @SerializedName("message")
        val message: String, // success
        @SerializedName("statusCode")
        val statusCode: Int, // 200
        @SerializedName("success")
        val success: Boolean // true
    )
}