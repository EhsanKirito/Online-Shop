package com.example.onlineshop.util.diffutil

import androidx.recyclerview.widget.DiffUtil
import com.example.onlineshop.data.network.model.ui.ProductItem

class ShopCallBack : DiffUtil.ItemCallback<ProductItem>() {
    override fun areItemsTheSame(oldItem: ProductItem, newItem: ProductItem): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: ProductItem, newItem: ProductItem): Boolean {
        return oldItem.id == newItem.id
    }
}