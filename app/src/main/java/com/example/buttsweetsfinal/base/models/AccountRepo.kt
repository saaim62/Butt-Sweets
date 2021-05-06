package com.example.buttsweetsfinal.base.models

import com.example.buttsweetsfinal.network.ApiCallbacks
import com.example.buttsweetsfinal.network.ApiManager

class AccountRepo(
    apiManager: ApiManager
) : BaseRepo(apiManager) {

    fun getProducts(apiCallbacks: ApiCallbacks, categoryId: String?) {
        categoryId?.let { apiManager.getProducts(apiCallbacks, categoryId = it) }
    }

}