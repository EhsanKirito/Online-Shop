package com.example.onlineshop.ui.features.category

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
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.onlineshop.R
import com.example.onlineshop.data.network.safeapicall.ResponseState
import com.example.onlineshop.databinding.FragmentCategoryBinding
import com.example.onlineshop.databinding.FragmentHomeBinding
import com.example.onlineshop.ui.features.category.adapter.CategoryAdapter
import com.example.onlineshop.ui.features.home.HomeFragmentDirections
import com.example.onlineshop.ui.features.home.HomeViewModel
import com.example.onlineshop.ui.features.home.adapter.HomeAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CategoryFragment : Fragment(R.layout.fragment_category) {
    private var _binding: FragmentCategoryBinding? = null
    private val viewModel : CategoryViewModel by viewModels()
    private val binding get() = _binding!!
    private lateinit var adapterCategory: CategoryAdapter
    private lateinit var recyclerViewCategory: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCategoryBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerViewInitCategory()
    }

    private fun recyclerViewInitCategory() {
        recyclerViewCategory = binding.rvCategoryList
        recyclerViewCategory.layoutManager = GridLayoutManager(binding.root.context, 2)
        adapterCategory = CategoryAdapter {
            findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToDetailsFragment(it)
            )
        }
        recyclerViewCategory.adapter = adapterCategory
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.categoryList.collect { responseState ->
                    when (responseState) {
                        is ResponseState.Error -> {
                            Toast.makeText(
                                requireContext(),
                                "ERROOOOOOOOOOOOOOOOOOR",
                                Toast.LENGTH_SHORT
                            ).show()
                            binding.progressBarCategory.isInvisible = true
                        }

                        ResponseState.Loading -> {
                            binding.apply {
                                progressBarCategory.isInvisible = false
                            }
                        }

                        is ResponseState.Success -> {
                            binding.progressBarCategory.isInvisible = true
                            adapterCategory.submitList(responseState.data)
                        }
                    }
                }
            }
        }
    }
}