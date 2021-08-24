package com.dindinn.miniassignment.ui.order_page

interface AdapterButtonClickCallBack {

    fun onAccept(position: Int)
    fun onExpire(position: Int)
    fun playAlarm(play: Boolean)

}