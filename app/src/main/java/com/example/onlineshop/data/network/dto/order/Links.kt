package com.example.onlineshop.data.network.dto.order

data class Links(
    val collection: List<Collection?>?,
    val customer: List<Customer?>?,
    val self: List<Self?>?
)