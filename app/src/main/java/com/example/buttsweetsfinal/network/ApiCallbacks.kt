package com.example.buttsweetsfinal.network

interface ApiCallbacks {

    fun doBeforeApiCall()

    fun doAfterApiCall()

    fun onApiFailure(errorCode: Int)

    fun onApiSuccess(apiResponse: BaseApiResponse)
}