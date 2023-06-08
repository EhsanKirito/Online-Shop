package com.example.onlineshop.ui.features.details


import android.os.Bundle
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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.onlineshop.data.network.model.ui.ProductItem
import com.example.onlineshop.data.network.safeapicall.ResponseState
import com.example.onlineshop.databinding.FragmentDetailsBinding
import com.example.onlineshop.ui.features.details.adapter.DetailsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailsFragment : Fragment() {
    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DetailsViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: DetailsAdapter
    companion object {
        const val PRODUCT_ID = "productId"
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
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
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.productDetails.collect { responseState ->
                    when (responseState) {
                        is ResponseState.Error -> {
                            Toast.makeText(
                                requireContext(),
                                "ERROOOOOOOOOOOOOOOOOOR",
                                Toast.LENGTH_SHORT
                            ).show()
                            binding.progressBar.isInvisible = true
                        }

                        ResponseState.Loading -> {
                            binding.apply {
                                progressBar.isInvisible = false
                                group.isInvisible = true
                            }
                        }

                        is ResponseState.Success -> {
                            binding.progressBar.isInvisible = true
                            binding.group.isInvisible = false
                            setToUi(responseState.data)
                        }
                    }
                }
            }
        }

    }

    private fun setToUi(productItem: ProductItem) {
        binding.apply {
            txtName.text = productItem.name
            txtPrice.text = productItem.price
            txtDescription.text = productItem.desc
        }
        adapter.submitList(productItem.imageUrls)
    }
    fun recyclerViewInit(){
        recyclerView = binding.rvProductImages
        recyclerView.layoutManager = LinearLayoutManager(binding.root.context, LinearLayoutManager.HORIZONTAL, true)
        adapter = DetailsAdapter {}
        recyclerView.adapter = adapter



    }

}