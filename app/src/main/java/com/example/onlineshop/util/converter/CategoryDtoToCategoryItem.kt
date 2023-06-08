package com.example.onlineshop.util.converter

import com.example.onlineshop.data.network.model.dto.category.CategoryDto
import com.example.onlineshop.data.network.model.dto.category.CategoryDtoItem
import com.example.onlineshop.data.network.model.ui.CategoryItem



    fun CategoryDto.toCategoryItem():List<CategoryItem> {
        return this?.map { category->
            category.toCategoryItem()
        } ?: emptyList()
    }

    fun CategoryDtoItem.toCategoryItem() = CategoryItem(id, name, description,image)