package com.example.onlineshop.ui.features.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.onlineshop.databinding.FragmentHomeBinding
import com.example.onlineshop.ui.features.home.adapter.HomeAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel : HomeViewModel by viewModels()

    private lateinit var adapterNewest: HomeAdapter
    private lateinit var adapterMostVisited: HomeAdapter
    private lateinit var adapterBest: HomeAdapter
    private lateinit var recyclerViewNewest: RecyclerView
    private lateinit var recyclerViewMostVisited: RecyclerView
    private lateinit var recyclerViewBest: RecyclerView
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
        recyclerViewInitMostVisited()
        recyclerViewInitBest()




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
        viewModel.products.observe(viewLifecycleOwner){
            adapterNewest.submitList(it)
        }
    }
    fun recyclerViewInitMostVisited(){
        recyclerViewMostVisited = binding.rvmostvisited
        recyclerViewMostVisited.layoutManager = LinearLayoutManager(binding.root.context, LinearLayoutManager.HORIZONTAL, true)
        adapterMostVisited = HomeAdapter {
            findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToDetailsFragment(it)
            )
        }
        recyclerViewMostVisited.adapter = adapterMostVisited
        viewModel.products.observe(viewLifecycleOwner){
            adapterMostVisited.submitList(it)
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
        recyclerViewBest.adapter = adapterNewest
        viewModel.products.observe(viewLifecycleOwner){
            adapterBest.submitList(it)
        }
    }

}