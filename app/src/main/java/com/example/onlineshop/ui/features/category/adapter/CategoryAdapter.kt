package com.example.onlineshop.ui.features.category.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.onlineshop.data.network.model.ui.CategoryItem
import com.example.onlineshop.databinding.CategoryListItemBinding
import com.example.onlineshop.util.diffutil.CategoryCallBack

class CategoryAdapter(
    private val onclick: (Int) -> Unit,
) :
    ListAdapter<CategoryItem, CategoryAdapter.ViewHolder>(CategoryCallBack()) {
    inner class ViewHolder(private val binding: CategoryListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.apply {
                setOnClickListener { getItem(adapterPosition).id?.let { id -> onclick(id) } }
            }
        }

        fun bind(item: CategoryItem) {
            with(binding) {
                txtName.text = item.name
                Glide.with(binding.root).load(item.image?.src).into(categoryPoster)

            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            CategoryListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(getItem(position))
}