package com.example.iconfind.core.uimvvnlayer.secondui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.iconfind.core.networkdata.apimanager.ApiResponse
import com.example.iconfind.core.networkdata.entity.model.CateDetailModel
import com.example.iconfind.core.networkdata.entity.model.CategoriesModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

class CateDetailViewModel : ViewModel() {

    suspend fun fetchApi(string: String) : CateDetailModel? {

        var job = viewModelScope.async(Dispatchers.IO){
            ApiResponse.cateDetailApi?.getCateDetail(string)

        }
        return  job.await()?.body()
    }


}