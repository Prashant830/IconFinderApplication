package com.example.iconfind.core.networkdata.apimanager.services

import com.example.iconfind.core.networkdata.entity.model.IconDetailModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CateDetailIcons {
    @GET("iconsets/{iconset_id}/icons")
    suspend fun getCateDetailIcons(@Path("iconset_id") iconset_id : String) : Response<IconDetailModel>
}