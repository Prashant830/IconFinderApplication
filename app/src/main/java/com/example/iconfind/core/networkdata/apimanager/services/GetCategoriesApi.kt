package com.example.iconfind.core.networkdata.apimanager.services

import com.example.iconfind.core.networkdata.entity.model.CategoriesModel
import retrofit2.Response
import retrofit2.http.GET

interface GetCategoriesApi {

    @GET("categories")
    suspend fun getCategories() : Response<CategoriesModel>
}