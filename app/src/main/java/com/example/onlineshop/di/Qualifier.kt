package com.example.onlineshop.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class BaseUrl

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ConsumerKey

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ConsumerSecret