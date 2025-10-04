package com.example.retrofit.Reposity

import com.example.retrofit.Model.ProductRequest
import com.example.retrofit.Network.RetrofitInstance

class ProductReposity{
    private val api = RetrofitInstance.api


    suspend fun listAll()= api.getAll()
    suspend fun get(id: Long)= api.getById(id)
    suspend fun create(req: ProductRequest)= api.create(req)
    suspend fun update(id: Long, request: ProductRequest)= api.update(id,req)
    suspend fun delete(id:Long)= api.delete(id)
    suspend fun search(name:String)=api.search(name)







}