package com.example.onlineshop.ui.features.productdetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlineshop.data.Repository.ShopRepository
import com.example.onlineshop.data.network.model.ui.ProductItem
import com.example.onlineshop.data.network.safeapicall.ResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val repository: ShopRepository, private val state: SavedStateHandle
) : ViewModel() {

    private val _productDetails =
        MutableStateFlow<ResponseState<ProductItem>>(ResponseState.Loading)
    val productDetails: StateFlow<ResponseState<ProductItem>> = _productDetails


    private val productId = state.get<Int>(DetailsFragment.PRODUCT_ID)
    init {
        productId?.let { getProductDetails(productId) }
    }

    private fun getProductDetails(id: Int) {
            viewModelScope.launch {
                repository.getProduct(id).collect { responseState ->
                    _productDetails.emit(responseState)
                }
            }
        }

}