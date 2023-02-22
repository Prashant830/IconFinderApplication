package com.example.iconfind.core.uimvvnlayer.thirdui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.iconfind.core.networkdata.apimanager.ApiResponse
import com.example.iconfind.core.networkdata.entity.model.IconDetailModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

class CatDetailIcoViewModel : ViewModel() {

    suspend fun fetchApi(string: String) : IconDetailModel? {

        var job = viewModelScope.async(Dispatchers.IO){
            ApiResponse.cateDetailIconsApi?.getCateDetailIcons(string)

        }
        return  job.await()?.body()
    }

}