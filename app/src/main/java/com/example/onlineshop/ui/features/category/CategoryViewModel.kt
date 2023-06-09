package com.example.onlineshop.ui.features.category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlineshop.data.Repository.ShopRepository
import com.example.onlineshop.data.network.model.ui.CategoryItem
import com.example.onlineshop.data.network.safeapicall.ResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(private val shopRepository: ShopRepository) :
    ViewModel() {

    private val _categoryList =
        MutableStateFlow<ResponseState<List<CategoryItem>>>(ResponseState.Loading)
    val categoryList: StateFlow<ResponseState<List<CategoryItem>>> = _categoryList

    init {
        getCategoryList()
    }

    private fun getCategoryList() {
        viewModelScope.launch {
            shopRepository.getAllCategories().collect { responseState ->
                _categoryList.emit(responseState)
            }
        }
    }
}