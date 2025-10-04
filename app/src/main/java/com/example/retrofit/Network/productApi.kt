package com.example.retrofit.Network

import com.example.retrofit.Model.Product

interface ProductApi {

    @GET ("products")
    suspend fun getAll(): List<Product>

    @GET ("products/{id}")
    suspend fun getById(@Path("id") id:Long): Product
    @POST("products")
    suspend fun create(@Body request: ProductRequest): Product

    @PUT("products/{id}")
    suspend fun update(@Path("id") id: Long, @Body request: ProductRequest): Product

    @DELETE("products/{id}")
    suspend fun delete(@Path("id") id: Long)

    @GET("products/search")
    suspend fun search(@Query("name") name: String): List<Product>

}