package com.example.iconfind.core.uimvvnlayer.uforthui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.iconfind.core.networkdata.apimanager.ApiResponse
import com.example.iconfind.core.networkdata.entity.model.IconDetailModel
import com.example.iconfind.core.networkdata.entity.model.SearchModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

class SearchViewModel : ViewModel() {

    suspend fun fetchApi(map: Map<String,String>) : SearchModel? {

        var job = viewModelScope.async(Dispatchers.IO){
            ApiResponse.searchIcApi?.getSearchIcon(map as Map<String, String>)

        }
        return  job.await()?.body()
    }
}