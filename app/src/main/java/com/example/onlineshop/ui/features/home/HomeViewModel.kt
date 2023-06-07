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
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val shopRepository: ShopRepository): ViewModel() {

    val _products = MutableLiveData<List<ProductItem>>()
        val products: LiveData<List<ProductItem>> = _products
init {
    getProducts()
}
    var job: Job? = null
    var page = 1

    fun getProducts(){
        Log.e("job", "start api" )
        if(job?.isActive == true) {
            Log.e("job", "job is active" )
            job?.cancel()
        }
        job = viewModelScope.launch {
            Log.e("job", "corotine" )
            shopRepository.getProducts(page).collectLatest{
                Log.e("job", "collect: $page" )
                when(it){
                    is ResponseState.Error -> Log.e("Home", "getProducts: failed" )
                    ResponseState.Loading -> Log.e("Home", "Loading Products")
                    is ResponseState.Success -> {
                        Log.e("job", "data" )
                        _products!!.postValue(it.data)
                        Log.e("job", "${it.data}" )
                    }
                }
            }
        }
    }
}