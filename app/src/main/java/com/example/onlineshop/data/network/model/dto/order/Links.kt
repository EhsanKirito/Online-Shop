package com.example.onlineshop.data.network.model.dto.order

data class Links(
    val collection: List<com.example.onlineshop.data.network.model.dto.order.Collection?>?,
    val customer: List<com.example.onlineshop.data.network.model.dto.order.Customer?>?,
    val self: List<com.example.onlineshop.data.network.model.dto.order.Self?>?
)