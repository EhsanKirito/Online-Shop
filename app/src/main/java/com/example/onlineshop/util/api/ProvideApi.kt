package com.example.onlineshop.util.api

import retrofit2.Retrofit

inline fun <reified T>provideApi(retrofit: Retrofit): T {
    return retrofit.create(T::class.java)
}