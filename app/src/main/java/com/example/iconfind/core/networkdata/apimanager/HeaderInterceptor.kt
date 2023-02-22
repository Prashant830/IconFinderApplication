package com.example.iconfind.core.networkdata.apimanager

import okhttp3.*

class HeaderInterceptor(private val tokenType: String , private val accessToken: String) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
            .newBuilder()
            .header(
                "Authorization","$tokenType $accessToken"
            ).build()
        return chain.proceed(request)
    }
}