package com.example.iconfind.core.networkdata.apimanager.services

import com.example.iconfind.core.networkdata.entity.model.CateDetailModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GetCateDetailApi {

    //https://api.iconfinder.com/v4/categories/{cateName}
    @GET("categories/{cateName}/iconsets?premium=true")
    suspend fun getCateDetail(@Path("cateName") catName: String) : Response<CateDetailModel>

}