package com.example.onlineshop.data.network.model.dto.customer

data class CustomerDto(
    val _links: com.example.onlineshop.data.network.model.dto.customer.Links?,
    val avatar_url: String?,
    val billing: com.example.onlineshop.data.network.model.dto.customer.Billing?,
    val date_created: String?,
    val date_created_gmt: String?,
    val date_modified: String?,
    val date_modified_gmt: String?,
    val email: String?,
    val first_name: String?,
    val id: Int?,
    val is_paying_customer: Boolean?,
    val last_name: String?,
    val meta_data: List<Any?>?,
    val role: String?,
    val shipping: com.example.onlineshop.data.network.model.dto.customer.Shipping?,
    val username: String?
)