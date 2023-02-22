package com.example.iconfind.core.uimvvnlayer.firstui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.iconfind.core.networkdata.apimanager.ApiResponse
import com.example.iconfind.core.networkdata.entity.model.CategoriesModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    suspend fun fetchApi() : CategoriesModel? {
        var job = viewModelScope.async(Dispatchers.IO){
            ApiResponse.catoApi?.getCategories()
        }
          return  job.await()?.body()
    }

}