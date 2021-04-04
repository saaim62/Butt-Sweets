package com.example.buttsweetsfinal.network

import android.content.Context
import com.example.buttsweetsfinal.R
import com.example.buttsweetsfinal.enums.DialogTheme
import com.example.buttsweetsfinal.providers.AlertDialogProvider
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiExecutor<T> : Callback<T> {

    private lateinit var mApiCallbacks: ApiCallbacks

    override fun onResponse(call: Call<T>, response: Response<T>) {
        mApiCallbacks.doAfterApiCall()
        if (response.isSuccessful) {
            try {
                val apiResponse = response.body() as BaseApiResponse
                mApiCallbacks.onApiSuccess(apiResponse)
            } catch (e: Exception) {
                if (e is ClassCastException) {
                    val apiResponse = BaseApiResponse()
                    val collectionResponse = response.body() as List<Any>
                    apiResponse.collection = collectionResponse
                    mApiCallbacks.onApiSuccess(apiResponse)
                } else {
                    throw e
                }
            }
        } else {
//            doOnError(response)
        }
    }

    override fun onFailure(call: Call<T>, t: Throwable) {
        mApiCallbacks.doAfterApiCall()
        mApiCallbacks.onApiFailure(HttpErrorCodes.Unknown.code)
    }

    fun addCallToQueue(context: Context, apiCall: Call<T>, apiCallbacks: ApiCallbacks) {
        if (Utils.isNetworkAvailable(context)) {
            this.mApiCallbacks = apiCallbacks
            apiCallbacks.doBeforeApiCall()
            apiCall.enqueue(this)
        } else {
            AlertDialogProvider.showAlertDialog(
                context,
                DialogTheme.ThemeWhite,
                context.getString(R.string.no_network_available)
            )
        }
    }
}