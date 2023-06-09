package com.example.onlineshop.ui.features.categorydetails.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.onlineshop.data.network.model.ui.CategoryItem
import com.example.onlineshop.data.network.model.ui.ProductItem
import com.example.onlineshop.databinding.CategoryDetailsItemBinding
import com.example.onlineshop.databinding.CategoryListItemBinding
import com.example.onlineshop.util.diffutil.CategoryCallBack
import com.example.onlineshop.util.diffutil.ShopCallBack

class CategoryDetailsAdapter(
    private val onclick: (Int) -> Unit,
) :
    ListAdapter<ProductItem, CategoryDetailsAdapter.ViewHolder>(ShopCallBack()) {
    inner class ViewHolder(private val binding: CategoryDetailsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.apply {
                setOnClickListener { getItem(adapterPosition).id?.let { id -> onclick(id) } }
            }
        }

        fun bind(item: ProductItem) {
            with(binding) {
                txtProductName.text = item.name
                txtProductPrice.text = item.price
                Glide.with(binding.root).load(item.imageUrl).into(productPoster)

            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            CategoryDetailsItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(getItem(position))
}