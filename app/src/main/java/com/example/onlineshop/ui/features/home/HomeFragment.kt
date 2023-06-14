package com.example.onlineshop.ui.features.home

import android.graphics.Color
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
import androidx.viewpager2.widget.ViewPager2
import com.example.onlineshop.R
import com.example.onlineshop.data.network.model.ui.ProductItem
import com.example.onlineshop.data.network.safeapicall.ResponseState
import com.example.onlineshop.databinding.FragmentHomeBinding
import com.example.onlineshop.ui.features.home.adapter.HomeAdapter
import com.example.onlineshop.ui.features.home.adapter.SliderAdapter
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderAnimations
import com.smarteist.autoimageslider.SliderView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel : HomeViewModel by viewModels()

    private lateinit var adapterNewest: HomeAdapter
    private lateinit var adapterMostVisited: HomeAdapter
    private lateinit var adapterBest: HomeAdapter
    private lateinit var recyclerViewNewest: RecyclerView
    private lateinit var recyclerViewMostViewed: RecyclerView
    private lateinit var recyclerViewBest: RecyclerView
    private lateinit var slider:SliderView
    private lateinit var adapterSlider:SliderAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerViewInitNewest()
        recyclerViewInitMostViewed()
        recyclerViewInitBest()
        viewPagerInit()

    }

    private fun setSlider(list:List<ProductItem>) {
        slider = binding.imageSlider
        adapterSlider = SliderAdapter(this.requireContext())
        for (i in list){
            adapterSlider.addItem(i)
        }
        slider.setSliderAdapter(adapterSlider)
        slider.setIndicatorAnimation(IndicatorAnimationType.WORM)
        slider.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION)
        slider.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH)
        slider.setIndicatorSelectedColor(Color.WHITE)
        slider.setIndicatorUnselectedColor(Color.GRAY)
        slider.setScrollTimeInSec(5)

        slider.startAutoCycle()
    }

    private fun viewPagerInit(){
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.productFeatured.collect { responseState ->
                    when (responseState) {
                        is ResponseState.Error -> {
                            Log.e("TAG", "viewPagerInit: error", )
                            Toast.makeText(
                                requireContext(),
                                R.string.networkError,
                                Toast.LENGTH_SHORT
                            ).show()
//                            binding.progressBarNewest.isInvisible = true
                        }

                        ResponseState.Loading -> {
                            Log.e("TAG", "viewPagerInit: loading", )
//                            binding.apply {
//                                progressBarNewest.isInvisible = false
//                            }
                        }

                        is ResponseState.Success -> {
//                            binding.progressBarNewest.isInvisible = true
                            Log.e("TAG", "viewPagerInit: success", )
                            setSlider(responseState.data)
                        }
                    }
                }
            }
        }
    }


    fun recyclerViewInitNewest(){
        recyclerViewNewest = binding.rvnewest
        recyclerViewNewest.layoutManager = LinearLayoutManager(binding.root.context, LinearLayoutManager.HORIZONTAL, true)
        adapterNewest = HomeAdapter {
            findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToDetailsFragment(it)
            )
        }
        recyclerViewNewest.adapter = adapterNewest
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.productNewest.collect { responseState ->
                    when (responseState) {
                        is ResponseState.Error -> {
                            Toast.makeText(
                                requireContext(),
                                R.string.networkError,
                                Toast.LENGTH_SHORT
                            ).show()
                            binding.progressBarNewest.isInvisible = true
                        }

                        ResponseState.Loading -> {
                            binding.apply {
                                progressBarNewest.isInvisible = false
                            }
                        }

                        is ResponseState.Success -> {
                            binding.progressBarNewest.isInvisible = true
                            adapterNewest.submitList(responseState.data)
                        }
                    }
                }
            }
        }
    }
    fun recyclerViewInitMostViewed(){
        recyclerViewMostViewed = binding.rvmostvisited
        recyclerViewMostViewed.layoutManager = LinearLayoutManager(binding.root.context, LinearLayoutManager.HORIZONTAL, true)
        adapterMostVisited = HomeAdapter {
            findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToDetailsFragment(it)
            )
        }
        recyclerViewMostViewed.adapter = adapterMostVisited
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.productMostVisited.collect { responseState ->
                    when (responseState) {
                        is ResponseState.Error -> {
                            Toast.makeText(
                                requireContext(),
                                R.string.networkError,
                                Toast.LENGTH_SHORT
                            ).show()
                            binding.progressBarMostViewed.isInvisible = true
                        }

                        ResponseState.Loading -> {
                            binding.apply {
                                progressBarMostViewed.isInvisible = false
                            }
                        }

                        is ResponseState.Success -> {
                            binding.progressBarMostViewed.isInvisible = true
                            adapterMostVisited.submitList(responseState.data)
                        }
                    }
                }
            }
        }
    }
    fun recyclerViewInitBest(){
        recyclerViewBest = binding.rvbest
        recyclerViewBest.layoutManager = LinearLayoutManager(binding.root.context, LinearLayoutManager.HORIZONTAL, true)
        adapterBest = HomeAdapter {
            findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToDetailsFragment(it)
            )
        }
        recyclerViewBest.adapter = adapterBest
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.productBest.collect { responseState ->
                    when (responseState) {
                        is ResponseState.Error -> {
                            Toast.makeText(
                                requireContext(),
                                R.string.networkError,
                                Toast.LENGTH_SHORT
                            ).show()
                            binding.progressBarBest.isInvisible = true
                        }

                        ResponseState.Loading -> {
                            binding.apply {
                                progressBarBest.isInvisible = false
                            }
                        }

                        is ResponseState.Success -> {
                            binding.progressBarBest.isInvisible = true
                            adapterBest.submitList(responseState.data)
                        }
                    }
                }
            }
        }
    }

}