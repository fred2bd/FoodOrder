package com.dindinn.miniassignment.ui.order_page.addons

import android.os.Handler
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dindinn.miniassignment.databinding.AddonsItemBinding
import com.dindinn.miniassignment.ui.order_page.OrderListResponse

class AddonsAdapter  : RecyclerView.Adapter<AddonsAdapter.ViewHolder>() {

    private var currentItemList = emptyList<OrderListResponse.Data.Addon>()


    class ViewHolder(val binding: AddonsItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {

        val binding =
            AddonsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        addData(holder, position)
    }

    private fun addData(holder: ViewHolder, position: Int) {
        holder.binding.apply {
          addonsQuantity.text="*".plus(currentItemList[position].quantity)
          addonsName.text=currentItemList[position].title

        }
    }

    override fun getItemCount(): Int {
        return currentItemList.size
    }


    fun setData(
        newItemList: List<OrderListResponse.Data.Addon>
    ) {
        val diffUtil = AddonsListDiffUtils(currentItemList, newItemList)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        currentItemList = newItemList
        diffResult.dispatchUpdatesTo(this)

    }
}