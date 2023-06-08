package com.example.onlineshop.util.diffutil


import androidx.recyclerview.widget.DiffUtil
import com.example.onlineshop.data.network.model.dto.product.Image


class ShopImageCallBack : DiffUtil.ItemCallback<Image>() {
    override fun areItemsTheSame(oldItem: Image, newItem: Image): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Image, newItem: Image): Boolean {
        return oldItem.id == newItem.id
    }
}