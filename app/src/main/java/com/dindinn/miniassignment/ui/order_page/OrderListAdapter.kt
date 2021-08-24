package com.dindinn.miniassignment.ui.order_page

import android.content.Context
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dindinn.miniassignment.R
import com.dindinn.miniassignment.databinding.OrderListItemBinding
import com.dindinn.miniassignment.ui.order_page.addons.AddonsAdapter
import com.dindinn.miniassignment.util.ConvertDateIntoMiliSec
import com.dindinn.miniassignment.util.GetTimeDifference
import java.util.concurrent.TimeUnit

class OrderListAdapter : RecyclerView.Adapter<OrderListAdapter.ViewHolder>() {

    private var currentItemList = emptyList<OrderListResponse.Data>()
    private val viewPool = RecyclerView.RecycledViewPool()
    private var expireTime: Long = 0
    private var alertAt: Long = 0
    lateinit var callback: AdapterButtonClickCallBack
    lateinit var context:Context


    class ViewHolder(val binding: OrderListItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {

        val binding =
            OrderListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        addData(holder, position)
    }

    private fun addData(holder: ViewHolder, position: Int) {


        holder.binding.apply {

            orderName.text = currentItemList[position].title
            orderId.text = "#".plus(currentItemList[position].id.toString())
            createTime.text =
                "at ".plus(ConvertDateIntoMiliSec.convert(currentItemList[position].expiredAt))
            totalQuantity.text = currentItemList[position].quantity.toString().plus("Items")
            val addonsAdapter = AddonsAdapter()
            addonsAdapter.setData(currentItemList[position].addon)
            addonsList.adapter = addonsAdapter
            addonsList.setRecycledViewPool(viewPool)


            expireTime = GetTimeDifference.result(
                currentItemList[position].expiredAt,
                (currentItemList[position].createdAt)
            ).toLong()
            alertAt = GetTimeDifference.result(
                currentItemList[position].alertedAt,
                (currentItemList[position].createdAt)
            ).toLong()

            autoRejectStatus.text =
                context.getString(R.string.auto_reject).plus(
                    expireTime.toString()
                ).plus(" mins")

            var expire = (TimeUnit.MINUTES.toMillis(expireTime) / 1000) / 60
            progressbar.max = expire.toInt()


            val countDownTimer = object :
                CountDownTimer(TimeUnit.MINUTES.toMillis(expireTime), 60000) {
                override fun onTick(millisUntilFinished: Long) {
                    var remainingTime = (millisUntilFinished / 1000) / 60 + 1

                    if (remainingTime < 1) {
                        autoRejectStatus.text =
                            context.getString(R.string.auto_reject).plus(millisUntilFinished / 1000).plus(" s")
                        progressbar.setProgress(10, true)
                    } else {
                        autoRejectStatus.text = context.getString(R.string.auto_reject).plus(remainingTime).plus(" mins")
                        progressbar.setProgress(remainingTime.toInt(), true)

                    }


                    if (remainingTime == alertAt) {
                        callback.playAlarm(true)
                    }


                }

                override fun onFinish() {
                    callback.playAlarm(false)
                    progressbar.setProgress(0, true);
                    autoRejectStatus.text = context.getString(R.string.rejected)
                    acceptBtn.text =  context.getString(R.string.expired)

                }


            }

            countDownTimer.start()





            acceptBtn.setOnClickListener {
                if (acceptBtn.text.toString() == context.getString(R.string.accept)) {
                    countDownTimer.cancel()
                    callback.playAlarm(false)
                    callback.onAccept(position)
                } else {

                    callback.onExpire(position)
                }

            }


        }
    }

    override fun getItemCount(): Int {
        return currentItemList.size
    }


    fun setData(
        newItemList: List<OrderListResponse.Data>,
        callback: AdapterButtonClickCallBack,
        context:Context
    ) {
        this.context=context
        this.callback = callback
        val diffUtil = OrderListDiffUtils(currentItemList, newItemList)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        currentItemList = newItemList
        diffResult.dispatchUpdatesTo(this)

    }
}