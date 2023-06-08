package com.example.onlineshop.data.network.remotedatasource

import com.example.onlineshop.data.network.model.ui.ProductItem
import com.example.onlineshop.data.network.safeapicall.safeApiCall
import com.example.onlineshop.data.network.service.ShopApiService
import com.example.onlineshop.util.converter.ProductDtoToProductItem
import com.example.onlineshop.util.converter.ProductToProductItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ShopDataSourceImp(private val shopApiService: ShopApiService):ShopRemoteDataSource {
    override fun getProduct(id: Int): Flow<ProductItem> =
        safeApiCall {shopApiService.getProduct(id)}.map{it.ProductToProductItem()}

    override fun getNewestProducts(): Flow<List<ProductItem>> =
        safeApiCall { shopApiService.getNewestProducts() }.map { it.ProductDtoToProductItem() }

    override fun getMostViewedProducts(): Flow<List<ProductItem>> =
        safeApiCall { shopApiService.getMostViewedProducts() }.map { it.ProductDtoToProductItem() }

    override fun getBestProducts(): Flow<List<ProductItem>> =
        safeApiCall { shopApiService.getBestProducts() }.map { it.ProductDtoToProductItem() }

}