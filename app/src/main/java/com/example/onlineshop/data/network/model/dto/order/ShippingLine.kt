package com.example.onlineshop.data.network.model.dto.order

data class ShippingLine(
    val id: Int?,
    val meta_data: List<com.example.onlineshop.data.network.model.dto.order.MetaDataX>?,
    val method_id: String?,
    val method_title: String?,
    val taxes: List<Any>?,
    val total: String?,
    val total_tax: String?
)