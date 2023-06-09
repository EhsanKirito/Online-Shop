package com.example.onlineshop.ui.features.categorydetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlineshop.data.Repository.ShopRepository
import com.example.onlineshop.data.network.model.ui.CategoryItem
import com.example.onlineshop.data.network.model.ui.ProductItem
import com.example.onlineshop.data.network.safeapicall.ResponseState
import com.example.onlineshop.ui.features.productdetails.DetailsFragment
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryDetailsViewModel @Inject constructor(
    private val repository: ShopRepository, state: SavedStateHandle
) : ViewModel() {

    private val _categoryDetails =
        MutableStateFlow<ResponseState<List<ProductItem>>>(ResponseState.Loading)
    val categoryDetails: StateFlow<ResponseState<List<ProductItem>>> = _categoryDetails


    private val categoryId = state.get<Int>(CategoryDetailsFragment.CATEGORY_ID)

    init {
        categoryId?.let { getCategoryDetails(categoryId) }
    }

    private fun getCategoryDetails(id: Int) {
        viewModelScope.launch {
            repository.getCategoriesById(id).collect { responseState ->
                _categoryDetails.emit(responseState)
            }
        }
    }

}