package com.dindinn.miniassignment.ui.ingredient


import com.google.gson.annotations.SerializedName

data class CategoryListResponse(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("status")
    val status: Status
) {
    data class Data(
        @SerializedName("id")
        val id: Int, // 10
        @SerializedName("title")
        val title: String // Bento
    )

    data class Status(
        @SerializedName("message")
        val message: String, // success
        @SerializedName("statusCode")
        val statusCode: Int, // 200
        @SerializedName("success")
        val success: Boolean // true
    )
}