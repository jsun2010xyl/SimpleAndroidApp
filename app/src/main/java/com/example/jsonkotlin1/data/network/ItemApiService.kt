package com.example.jsonkotlin1.data.network

import com.example.jsonkotlin1.data.ItemList
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


interface ItemApiService {

    @GET("hiring.json")
    fun getItemList(

    ): Deferred<ItemList>

    companion object {
        operator fun invoke(connectivityInterceptor: ConnectivityInterceptor): ItemApiService {

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(connectivityInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://fetch-hiring.s3.amazonaws.com/")
                .addCallAdapterFactory(CoroutineCallAdapterFactory()) // for Deferred
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ItemApiService::class.java)
        }


    }
}