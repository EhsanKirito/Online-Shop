package com.example.onlineshop.ui.features.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.onlineshop.data.network.model.ui.ProductItem
import com.example.onlineshop.databinding.ProductItemBinding
import com.example.onlineshop.util.diffutil.ShopCallBack

class HomeAdapter (
    private val onclick: (Int) -> Unit,
) :
    ListAdapter<ProductItem, HomeAdapter.ViewHolder>(ShopCallBack()) {
    inner class ViewHolder(private val binding: ProductItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.apply {
                setOnClickListener { getItem(adapterPosition).id?.let { id -> onclick(id) } }
            }
        }

        fun bind(item: ProductItem) {
            with(binding) {
                txtname.text = item.name
                txtprice.text = item.price
                Glide.with(binding.root).load(item.imageUrl).into(productposter)

            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(ProductItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(getItem(position))
}