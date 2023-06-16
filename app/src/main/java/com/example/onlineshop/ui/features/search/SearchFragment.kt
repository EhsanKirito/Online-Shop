package com.example.onlineshop.ui.features.search

import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.Spinner
import android.widget.Toast
import androidx.core.view.isInvisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.onlineshop.R
import com.example.onlineshop.data.network.safeapicall.ResponseState
import com.example.onlineshop.databinding.FragmentSearchBinding
import com.example.onlineshop.ui.features.home.HomeFragmentDirections
import com.example.onlineshop.ui.features.search.adapter.SearchAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : Fragment(), AdapterView.OnItemSelectedListener {
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerViewSearch: RecyclerView
    private lateinit var adapterSearch: SearchAdapter
    private val viewModel : SearchViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        spinnerOrderInit()
        spinnerOrderbyInit()
        spinnerCategoryInit()
        searchBoxInit()

    }

    private fun spinnerOrderInit() {
        val spinnerOrder = binding.spnOrder
        if (spinnerOrder != null) {
            ArrayAdapter.createFromResource(
                requireContext(),
                R.array.search_order,
                android.R.layout.simple_spinner_item
            ).also {
                it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                binding.spnOrder.adapter = it
            }
        }
        spinnerOrder.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                when (position){
                    0 -> viewModel.order = "asc"
                    1 -> viewModel.order = "desc"
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }
    }
    private fun spinnerOrderbyInit() {
        val spinnerSorting = binding.spnSorting
        if (spinnerSorting != null) {
            ArrayAdapter.createFromResource(
                requireContext(),
                R.array.search_orderby,
                android.R.layout.simple_spinner_item
            ).also {
                it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                binding.spnSorting.adapter = it
            }
        }
        spinnerSorting.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                when (position){
                    0 -> viewModel.orderby = "popularity"
                    1 -> viewModel.orderby = "price"
                    2 -> viewModel.orderby = "date"
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }
    }
    private fun spinnerCategoryInit() {
        val spinnerCategory = binding.spnCategory
        if (spinnerCategory != null) {
            ArrayAdapter.createFromResource(
                requireContext(),
                R.array.search_category,
                android.R.layout.simple_spinner_item
            ).also {
                it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                binding.spnCategory.adapter = it
            }
        }
        spinnerCategory.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                when (position){
                    0 -> viewModel.category = 121
                    1 -> viewModel.category = 63
                    2 -> viewModel.category = 64
                    3 -> viewModel.category = 52
                    4 -> viewModel.category = 102
                    5 -> viewModel.category = 81
                    6 -> viewModel.category = 77
                    7 -> viewModel.category = 76
                    8 -> viewModel.category = 79
                    9 -> viewModel.category = 119
                    10 -> viewModel.category = 70
                    11 -> viewModel.category = 124
                    12 -> viewModel.category = 53
                    13 -> viewModel.category = 86
                    14 -> viewModel.category = 62
                    15 -> viewModel.category = 129
                    16 -> viewModel.category = 95
                    17 -> viewModel.category = 82
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }
    }




    fun searchBoxInit(){
        binding.searchHome.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                if (p0 == ""){
                    viewModel.job?.cancel()
                }else {
                    viewModel.getProductSearch()
                    recyclerViewInitSearch(p0)
                }
                return true
            }
        })
    }

    fun recyclerViewInitSearch(p0: String?) {
        if (p0 != null) {
            viewModel.query = p0
        }
        recyclerViewSearch = binding.rvSearch
        recyclerViewSearch.layoutManager = LinearLayoutManager(binding.root.context, LinearLayoutManager.VERTICAL, true)
        adapterSearch = SearchAdapter {
            findNavController().navigate(
                SearchFragmentDirections.actionSearchFragmentToDetailsFragment(it)
            )
        }
        recyclerViewSearch.adapter = adapterSearch
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.productSearch.collect { responseState ->
                    when (responseState) {
                        is ResponseState.Error -> {
                            Toast.makeText(
                                requireContext(),
                                R.string.networkError,
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        ResponseState.Loading -> {
                            binding.apply {
                            }
                        }

                        is ResponseState.Success -> {
                            binding.apply {
                            }
                            adapterSearch.submitList(responseState.data)
                        }
                    }
                }
            }
        }
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        TODO("Not yet implemented")
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

}