package com.example.onlineshop.data.network.remotedatasource

import com.example.onlineshop.data.network.model.ui.ProductItem
import com.example.onlineshop.data.network.safeapicall.safeApiCall
import com.example.onlineshop.data.network.service.ShopApiService
import com.example.onlineshop.util.converter.ProductDtoToProductItem
import com.example.onlineshop.util.converter.ProductToProductItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ShopDataSourceImp(private val shopApiService: ShopApiService):ShopRemoteDataSource {
    override fun getProducts(page: Int): Flow<List<ProductItem>> =
        safeApiCall {shopApiService.getProducts()}.map{
            it.ProductDtoToProductItem()

        }


    override fun getProduct(id: Int): Flow<ProductItem> =
        safeApiCall {shopApiService.getProduct(id)}.map{it.ProductToProductItem()}
}