package com.example.onlineshop.ui.features.home.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.onlineshop.data.network.model.ui.ProductItem
import com.example.onlineshop.databinding.ImageSliderItemBinding
import com.smarteist.autoimageslider.SliderViewAdapter


class SliderAdapter(private val context: Context) :
    SliderViewAdapter<SliderAdapter.SliderAdapterVH>() {
    private var mSliderItems: MutableList<ProductItem> = ArrayList()
    fun renewItems(sliderItems: MutableList<ProductItem>) {
        mSliderItems = sliderItems
        notifyDataSetChanged()
    }

    fun deleteItem(position: Int) {
        mSliderItems.removeAt(position)
        notifyDataSetChanged()
    }

    fun addItem(sliderItem: ProductItem) {
        mSliderItems.add(sliderItem)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup): SliderAdapterVH {
        val itemBinding = ImageSliderItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SliderAdapterVH(itemBinding)
    }

    override fun onBindViewHolder(viewHolder: SliderAdapterVH, position: Int) {
        val sliderItem: ProductItem = mSliderItems[position]
        viewHolder.textViewDescription.text = sliderItem.desc
        viewHolder.textViewDescription.textSize = 16f
        viewHolder.textViewDescription.setTextColor(Color.BLACK)
        Glide.with(viewHolder.itemView)
            .load(sliderItem.imageUrl)
            .fitCenter()
            .into(viewHolder.imageViewBackground)
        viewHolder.itemView.setOnClickListener {
            Toast.makeText(
                context,
                "This is item in position $position",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun getCount(): Int {
        //slider view count could be dynamic size
        return mSliderItems.size
    }

    class SliderAdapterVH(binding: ImageSliderItemBinding) :
        ViewHolder(binding.root) {
        lateinit var itemViews:View
        var imageViewBackground: ImageView
        var imageGifContainer: ImageView
        var textViewDescription: TextView

        init {
            imageViewBackground = binding.ivAutoImageSlider
            imageGifContainer = binding.ivGifContainer
            textViewDescription = binding.tvAutoImageSlider
            this.itemViews = itemView
        }
    }
}