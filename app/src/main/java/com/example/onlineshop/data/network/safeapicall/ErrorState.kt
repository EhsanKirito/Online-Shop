package com.example.onlineshop.data.network.safeapicall

sealed class ErrorState {
    data class ErrorCode(val code: String): ErrorState()
    data class ErrorMessage(val message: String) : ErrorState()
}