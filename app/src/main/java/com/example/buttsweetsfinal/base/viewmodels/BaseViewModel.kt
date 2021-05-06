package com.example.buttsweetsfinal.base.viewmodels

import androidx.lifecycle.ViewModel
import com.example.buttsweetsfinal.base.models.BaseRepo
import com.example.buttsweetsfinal.network.ApiCallbacks
import com.example.buttsweetsfinal.network.BaseApiResponse

open class BaseViewModel(private val baseRepo: BaseRepo) : ViewModel(), ApiCallbacks {

    override fun doBeforeApiCall() {

    }

    override fun doAfterApiCall() {
    }

//    override fun onNoNetworkAvailable() {
//    }

    override fun onApiFailure(errorCode: Int) {
    }

    override fun onApiSuccess(apiResponse: BaseApiResponse) {
    }
}