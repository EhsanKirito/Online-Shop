package com.example.onlineshop.di.module

import com.example.onlineshop.data.network.remotedatasource.ShopDataSourceImp
import com.example.onlineshop.data.network.remotedatasource.ShopRemoteDataSource
import com.example.onlineshop.data.network.service.ShopApiService
import com.example.onlineshop.di.BaseUrl
import com.example.onlineshop.di.ConsumerKey
import com.example.onlineshop.di.ConsumerSecret
import com.example.onlineshop.util.api.provideApi
import com.example.onlineshop.util.networkconsts.ServiceConsts.CONSUMER_KEY
import com.example.onlineshop.util.networkconsts.ServiceConsts.CONSUMER_KEY_VALUE
import com.example.onlineshop.util.networkconsts.ServiceConsts.CONSUMER_SECRET
import com.example.onlineshop.util.networkconsts.ServiceConsts.CONSUMER_SECRET_VALUE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ShopServiceModule {

    @Provides
    @Singleton
    @ConsumerKey
    fun provideConsumerKey(): String = CONSUMER_KEY

    @Provides
    @Singleton
    @ConsumerSecret
    fun provideConsumerSecret(): String = CONSUMER_SECRET

    @Provides
    @Singleton
    fun provideInterceptor(@ConsumerKey ck: String, @ConsumerSecret cs: String): Interceptor =
        Interceptor { chain ->
            val url = chain.request()
                .url
                .newBuilder()
                .build()
            val request = chain.request()
                .newBuilder()
                .url(url)
                .addHeader(CONSUMER_KEY_VALUE, ck)
                .addHeader(CONSUMER_SECRET_VALUE, cs)
                .build()
            chain.proceed(request)
        }

    @Provides
    @Singleton
    fun provideOkHttpLog(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }


    @Provides
    @Singleton
    fun provideOkHttpClient(
        interceptor: Interceptor,
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(httpLoggingInterceptor)
        .addInterceptor(interceptor)
        .build()


    @Provides
    @Singleton
    fun provideRetrofit(
        client: OkHttpClient,
        @BaseUrl baseUrl: String
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Singleton
    @Provides
    fun provideMovieService(retrofit: Retrofit): ShopApiService =
        provideApi<ShopApiService>(retrofit)

    @Singleton
    @Provides
    fun provideRemoteDataSource(shopService: ShopApiService): ShopRemoteDataSource =
        ShopDataSourceImp(shopService)
}