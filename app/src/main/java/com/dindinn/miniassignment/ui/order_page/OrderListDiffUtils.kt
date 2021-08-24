package com.dindinn.miniassignment.ui.order_page

import androidx.recyclerview.widget.DiffUtil

class OrderListDiffUtils(
    private val currentItemList: List<OrderListResponse.Data>,
    private val newItemList: List<OrderListResponse.Data>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return currentItemList.size
    }

    override fun getNewListSize(): Int {
        return newItemList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return currentItemList[oldItemPosition].id == newItemList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when {
            currentItemList[oldItemPosition] != newItemList[newItemPosition] -> {
                false
            }

            else -> true
        }
    }
}