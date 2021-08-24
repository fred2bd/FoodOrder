package com.dindinn.miniassignment.ui.ingredient

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dindinn.miniassignment.R
import com.dindinn.miniassignment.databinding.ItemLayoutBinding


class CategoryItemListAdapter : RecyclerView.Adapter<CategoryItemListAdapter.ViewHolder>() {
    private var currentItemList = emptyList<CategoryItemList.Item>()
    private lateinit var context:Context


    class ViewHolder(val binding: ItemLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding =
            ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }


    override fun getItemCount(): Int {
        return currentItemList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        addData(holder, position)
    }

    private fun addData(holder: ViewHolder, position: Int) {

        holder.binding.apply {
            title.text = currentItemList[position].title
            quantity.text = currentItemList[position].quantity.toString()
            if (!currentItemList[position].isAvailable) {
                quantity.setTextColor(ContextCompat.getColor(context, R.color.light_gray))
                availableLabel.setBackgroundColor(ContextCompat.getColor(context, R.color.light_gray))
            }

        }


    }


    fun setData(
        newItemList: List<CategoryItemList.Item>,
        context: Context
    ) {
        this.context=context
        val diffUtil = CategoryItemListDiffUtils(currentItemList, newItemList)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        currentItemList = newItemList
        diffResult.dispatchUpdatesTo(this)

    }
}