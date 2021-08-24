package com.dindinn.miniassignment.ui.ingredient

import androidx.recyclerview.widget.DiffUtil

class CategoryItemListDiffUtils(
    private val currentItemList: List<CategoryItemList.Item>,
    private val newItemList: List<CategoryItemList.Item>
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