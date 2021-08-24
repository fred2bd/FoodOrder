package com.dindinn.miniassignment.util

import java.text.SimpleDateFormat
import java.util.*

object GetTimeDifference {


    fun result(startTime: String, expireTime: String): Int {
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm+SSS'Z'")
        val startDate: Date =sdf.parse(startTime)
        val expireDate: Date =sdf.parse(expireTime)


        val diff: Long =startDate.time-expireDate.time
        var days = (diff / (1000 * 60 * 60 * 24)).toInt()
        var hours = ((diff - 1000 * 60 * 60 * 24 * days) / (1000 * 60 * 60)).toInt()
        var min = (diff - 1000 * 60 * 60 * 24 * days - 1000 * 60 * 60 * hours).toInt() / (1000 * 60)

        Timer("time ".plus(min))

        return min


    }

}