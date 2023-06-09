package com.example.onlineshop.ui.features.categorydetails

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isInvisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.onlineshop.R
import com.example.onlineshop.data.network.model.ui.ProductItem
import com.example.onlineshop.data.network.safeapicall.ResponseState
import com.example.onlineshop.databinding.FragmentCategoryDetailsBinding
import com.example.onlineshop.ui.features.categorydetails.adapter.CategoryDetailsAdapter
import com.example.onlineshop.ui.features.home.HomeFragmentDirections
import com.example.onlineshop.ui.features.productdetails.adapter.DetailsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CategoryDetailsFragment : Fragment() {
    private var _binding: FragmentCategoryDetailsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CategoryDetailsViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CategoryDetailsAdapter

    companion object {
        const val CATEGORY_ID = "categoryId"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCategoryDetailsBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerViewInit()
        collectData()


    }

    private fun collectData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                Log.e("TAG", "collectData: before collect" )
                viewModel.categoryDetails.collect { responseState ->
                    Log.e("TAG", "collectData: before when" )
                    when (responseState) {
                        is ResponseState.Error -> {
                            Log.e("TAG", "collectData: ${responseState.error}" )
                            Toast.makeText(
                                requireContext(),
                                R.string.networkError,
                                Toast.LENGTH_SHORT
                            ).show()
                            binding.progressBarCategoryDetails.isInvisible = true
                        }

                        ResponseState.Loading -> {
                            Log.e("TAG", "collectData: loading" )
                            binding.apply {
                                progressBarCategoryDetails.isInvisible = false

                            }
                        }

                        is ResponseState.Success -> {
                            Log.e("TAG", "collectData: success" )
                            binding.progressBarCategoryDetails.isInvisible = true
                            adapter.submitList(responseState.data)
                        }
                    }
                }
            }
        }
    }

    fun recyclerViewInit() {
        recyclerView = binding.rvCategoryDetails
        recyclerView.layoutManager =
            LinearLayoutManager(binding.root.context)
        adapter = CategoryDetailsAdapter {productId->
            findNavController().navigate(
                CategoryDetailsFragmentDirections.todetailsFragment(productId)
            )
        }
        recyclerView.adapter = adapter
    }
}