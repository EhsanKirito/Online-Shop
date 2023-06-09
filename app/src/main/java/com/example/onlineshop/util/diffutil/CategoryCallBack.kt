package com.example.onlineshop.util.diffutil

import androidx.recyclerview.widget.DiffUtil
import com.example.onlineshop.data.network.model.ui.CategoryItem

class CategoryCallBack: DiffUtil.ItemCallback<CategoryItem>() {
    override fun areItemsTheSame(oldItem: CategoryItem, newItem: CategoryItem): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: CategoryItem, newItem: CategoryItem): Boolean {
        return oldItem.id == newItem.id
    }
}