package com.example.onlineshop.ui.features.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlineshop.data.Repository.ShopRepository
import com.example.onlineshop.data.network.model.ui.ProductItem
import com.example.onlineshop.data.network.safeapicall.ResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val shopRepository: ShopRepository): ViewModel() {

    private val _productSearch =
        MutableStateFlow<ResponseState<List<ProductItem>>>(ResponseState.Loading)
    val productSearch: StateFlow<ResponseState<List<ProductItem>>> = _productSearch

    var job: Job? = null
    var query = ""
    var category:Int = 52
    var orderby = "popularity"
    var order = "desc"

    fun getProductSearch(){
        if(job?.isActive == true) {
            job?.cancel()
        }
        job = viewModelScope.launch {
            delay(300)
            shopRepository.getDetailedSearchedProducts(query, category, orderby, order).collect { responseState ->
                _productSearch.emit(responseState)
            }
        }
    }
}