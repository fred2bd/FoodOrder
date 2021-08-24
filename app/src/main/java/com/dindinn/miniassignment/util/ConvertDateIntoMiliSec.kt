package com.dindinn.miniassignment.util

import java.text.SimpleDateFormat
import java.util.*

object ConvertDateIntoMiliSec {

    fun convert(date: String): String {
        var sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm+SSS'Z'", Locale.getDefault())
        var date: Date = sdf.parse(date)
        val cal = Calendar.getInstance()
        cal.time = date
        val time = cal.get(Calendar.HOUR_OF_DAY).toString().plus(":").plus(cal.get(Calendar.MINUTE))

        sdf = SimpleDateFormat("H:mm")
        date = sdf.parse(time)


        return  SimpleDateFormat("K:mm a").format(date)
    }

}