package com.dindinn.miniassignment.util


import android.content.Context
import java.io.InputStream


object ParseJsonData {

    fun readJSONFromAsset(context:Context): String? {
        var json: String? = null
        try {
            val  inputStream:InputStream = context.assets.open("order_data.json")
            json = inputStream.bufferedReader().use{it.readText()}
        } catch (ex: Exception) {
            ex.printStackTrace()
            return null
        }
        return json
    }

}