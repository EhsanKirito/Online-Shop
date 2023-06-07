package com.example.onlineshop.ui.features.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.onlineshop.databinding.FragmentHomeBinding
import com.example.onlineshop.ui.features.home.adapter.HomeAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel : HomeViewModel by viewModels()

    private lateinit var adapter1: HomeAdapter
    private lateinit var adapter2: HomeAdapter
    private lateinit var adapter3: HomeAdapter
    private lateinit var recyclerView1: RecyclerView
    private lateinit var recyclerView2: RecyclerView
    private lateinit var recyclerView3: RecyclerView
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
        recyclerViewInit1()
        recyclerViewInit2()
        recyclerViewInit3()




    }

    fun recyclerViewInit1(){
        recyclerView1 = binding.rvnewest
        recyclerView1.layoutManager = LinearLayoutManager(binding.root.context, LinearLayoutManager.HORIZONTAL, true)
        adapter1 = HomeAdapter {
            findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToDetailsFragment(it)
            )
        }
        recyclerView1.adapter = adapter1
        viewModel.products.observe(viewLifecycleOwner){
            adapter1.submitList(it)
        }
    }
    fun recyclerViewInit2(){
        recyclerView2 = binding.rvmostvisited
        recyclerView2.layoutManager = LinearLayoutManager(binding.root.context, LinearLayoutManager.HORIZONTAL, true)
        adapter2 = HomeAdapter {
            findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToDetailsFragment(it)
            )
        }
        recyclerView2.adapter = adapter2
        viewModel.products.observe(viewLifecycleOwner){
            adapter2.submitList(it)
        }
    }
    fun recyclerViewInit3(){
        recyclerView3 = binding.rvbest
        recyclerView3.layoutManager = LinearLayoutManager(binding.root.context, LinearLayoutManager.HORIZONTAL, true)
        adapter3 = HomeAdapter {
            findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToDetailsFragment(it)
            )
        }
        recyclerView3.adapter = adapter1
        viewModel.products.observe(viewLifecycleOwner){
            adapter3.submitList(it)
        }
    }

}