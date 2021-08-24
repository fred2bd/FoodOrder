package com.dindinn.miniassignment.ui.ingredient

import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.dindinn.miniassignment.FragmentToActivityCommunicationViewModel
import com.dindinn.miniassignment.base.BaseFragmentWithBinding
import com.dindinn.miniassignment.databinding.FragmentIngredientBinding
import com.google.android.material.tabs.TabLayout
import timber.log.Timber


class IngredientFragment :
    BaseFragmentWithBinding<FragmentIngredientBinding>(FragmentIngredientBinding::inflate) {

    private val categoryListViewModel: CategoryListViewModel by viewModels()
    private val categoryItemList: CategoryItemListViewModel by viewModels()
    private val communicationViewModel: FragmentToActivityCommunicationViewModel by  activityViewModels()
    lateinit var items: MutableList<CategoryItemList.Item>
    private var categoryId = 0

    private val categoryItemListAdapter: CategoryItemListAdapter by lazy {
        CategoryItemListAdapter()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        communicationViewModel.showProgressBar(true)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        categoryListViewModel.callApi(requireContext())
        categoryListViewModel.responseLivedata.observe(viewLifecycleOwner, Observer {

            for (i in it.data) {
                Timber.d(i.id.toString())
                binding.tabLayout.addTab(binding.tabLayout.newTab().setText(i.title).setId(i.id))
            }

            Timber.d(it.toString())
        })




        categoryItemList.responseLivedata.observe(viewLifecycleOwner, Observer {

            if (it.items.isNotEmpty()) {
                if (binding.noItemLabel.isVisible) {
                    binding.noItemLabel.visibility = View.GONE

                }



                loadData(it)
            } else {
                binding.noItemLabel.visibility = View.VISIBLE
            }
            communicationViewModel.showProgressBar(false)


        })



        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                communicationViewModel.showProgressBar(true)

                if (tab != null) {
                    categoryId = tab.id
                }


                tab?.let { categoryItemList.callApi(categoryId, requireContext()) }

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })



        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {

                searchFun(query)
                binding.searchView.setQuery("", false);


                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true

            }
        })


        binding.searchView.setOnCloseListener(object : SearchView.OnCloseListener {
            override fun onClose(): Boolean {
                binding.itemList.apply {
                    categoryItemListAdapter.setData(items,requireContext())
                    categoryItemListAdapter.notifyDataSetChanged()
                }

                if (items.isNotEmpty()) {
                    binding.noItemLabel.visibility = View.GONE

                }



                return false
            }
        })

    }

    private fun loadData(it: CategoryItemList) {
        items = it.items.toMutableList()

        binding.itemList.apply {

            adapter = categoryItemListAdapter
            categoryItemListAdapter.setData(items,requireContext())

        }
    }


    fun searchFun(query: String?) {
        var filterData = mutableListOf<CategoryItemList.Item>()

        for (i in items) {
            if (query?.let { i.title.contains(it, ignoreCase = true) } == true) {
                filterData.add(i)
            }
        }



        if (filterData.isEmpty()) {
            binding.noItemLabel.visibility = View.VISIBLE
        } else {
            binding.noItemLabel.visibility = View.GONE

        }

        binding.itemList.apply {
            categoryItemListAdapter.setData(filterData,requireContext())
            categoryItemListAdapter.notifyDataSetChanged()
        }


    }

}