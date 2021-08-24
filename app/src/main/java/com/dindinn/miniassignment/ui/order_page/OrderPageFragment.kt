package com.dindinn.miniassignment.ui.order_page

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.dindinn.miniassignment.FragmentToActivityCommunicationViewModel
import com.dindinn.miniassignment.R
import com.dindinn.miniassignment.base.BaseFragmentWithBinding
import com.dindinn.miniassignment.databinding.FragmentOrderPageBinding
import java.util.*

class OrderPageFragment :
    BaseFragmentWithBinding<FragmentOrderPageBinding>(FragmentOrderPageBinding::inflate),
    AdapterButtonClickCallBack {

    private val orderViewModel: OrderViewModel by viewModels()
    private val orderListAdapter: OrderListAdapter by lazy { OrderListAdapter() }
    lateinit var cartItemsMutableList: MutableList<OrderListResponse.Data>
    private val communicationViewModel: FragmentToActivityCommunicationViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        communicationViewModel.showProgressBar(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        callApi()
        observers()
        binding.ingredientBtn.setOnClickListener {
            view.findNavController().navigate(R.id.action_orderPageFragment_to_ingredientFragment)
        }

    }

    private fun callApi() {
        orderViewModel.callOrderApi(requireContext())
    }

    private fun observers() {
        orderViewModel.responseLivedata.observe(viewLifecycleOwner, Observer {

            binding.orderList.adapter = orderListAdapter
            cartItemsMutableList = it.data.toMutableList()
            orderListAdapter.setData(cartItemsMutableList, this,requireContext())
            communicationViewModel.showProgressBar(false)

        })
        orderViewModel.errorLivedata.observe(viewLifecycleOwner, Observer {
            communicationViewModel.showProgressBar(false)

        })
    }

    override fun onAccept(position: Int) {
        Timer("Item removed")


        cartItemsMutableList.removeAt(position)
        orderListAdapter.notifyItemRemoved(position)

    }

    override fun onExpire(position: Int) {
        cartItemsMutableList.removeAt(position)
        orderListAdapter.notifyItemRemoved(position)

    }

    override fun playAlarm(play: Boolean) {
        communicationViewModel.play(play)
    }


}