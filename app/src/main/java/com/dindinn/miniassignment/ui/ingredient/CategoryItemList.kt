package com.dindinn.miniassignment.ui.ingredient


import com.google.gson.annotations.SerializedName

data class CategoryItemList(
    @SerializedName("items")
    val items: List<Item>,
    @SerializedName("status")
    val status: Status
) {
    data class Item(
        @SerializedName("id")
        val id: Int, // 24
        @SerializedName("isAvailable")
        val isAvailable: Boolean, // true
        @SerializedName("quantity")
        val quantity: Int, // 3
        @SerializedName("title")
        val title: String // Burger
    )

    data class Status(
        @SerializedName("id")
        val id: Int, // 30
        @SerializedName("message")
        val message: String, // success
        @SerializedName("statusCode")
        val statusCode: Int, // 200
        @SerializedName("success")
        val success: Boolean // true
    )
}