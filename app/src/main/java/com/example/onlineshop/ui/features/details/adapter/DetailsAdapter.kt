package com.example.onlineshop.ui.features.details.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.onlineshop.data.network.model.dto.product.Image
import com.example.onlineshop.databinding.DetailsImageItemBinding
import com.example.onlineshop.util.diffutil.ShopImageCallBack

class DetailsAdapter (
    private val onclick: (Int) -> Unit,
    ) :
    ListAdapter<Image, DetailsAdapter.ViewHolder>(ShopImageCallBack()) {
        inner class ViewHolder(private val binding: DetailsImageItemBinding) :
            RecyclerView.ViewHolder(binding.root) {
            init {
                binding.root.apply {
                    setOnClickListener { getItem(adapterPosition).id?.let { id -> onclick(id) } }
                }
            }

            fun bind(item: Image) {
                with(binding) {

                    Glide.with(binding.root).load(item.src).into(imageView)

                }
            }


        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder(DetailsImageItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

        override fun onBindViewHolder(holder: ViewHolder, position: Int) =
            holder.bind(getItem(position))
    }