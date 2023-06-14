package com.example.onlineshop.ui.features.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlineshop.data.Repository.ShopRepository
import com.example.onlineshop.data.network.model.ui.ProductItem
import com.example.onlineshop.data.network.safeapicall.ResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val shopRepository: ShopRepository): ViewModel() {

    private val _productNewest =
        MutableStateFlow<ResponseState<List<ProductItem>>>(ResponseState.Loading)
    val productNewest: StateFlow<ResponseState<List<ProductItem>>> = _productNewest

    private val _productMostVisited =
        MutableStateFlow<ResponseState<List<ProductItem>>>(ResponseState.Loading)
    val productMostVisited: StateFlow<ResponseState<List<ProductItem>>> = _productMostVisited

    private val _productBest =
        MutableStateFlow<ResponseState<List<ProductItem>>>(ResponseState.Loading)
    val productBest: StateFlow<ResponseState<List<ProductItem>>> = _productBest

    private val _productFeatured =
        MutableStateFlow<ResponseState<List<ProductItem>>>(ResponseState.Loading)
    val productFeatured: StateFlow<ResponseState<List<ProductItem>>> = _productFeatured

init {
    getProductNewest()
    getMostViewedProducts()
    getBestProducts()
    getFeaturedProducts()
}


    fun getProductNewest(){

        viewModelScope.launch {
            shopRepository.getNewestProducts().collect { responseState ->
                _productNewest.emit(responseState)
            }
        }
    }

    fun getMostViewedProducts(){
        viewModelScope.launch {
            shopRepository.getMostViewedProducts().collect { responseState ->
                _productMostVisited.emit(responseState)
            }
        }
    }

    fun getBestProducts(){
        viewModelScope.launch {
            shopRepository.getBestProducts().collect { responseState ->
                _productBest.emit(responseState)
            }
        }
    }

    fun getFeaturedProducts(){
        viewModelScope.launch {
            shopRepository.getFeatureProducts().collect { responseState ->
                _productFeatured.emit(responseState)
            }
        }
    }
}