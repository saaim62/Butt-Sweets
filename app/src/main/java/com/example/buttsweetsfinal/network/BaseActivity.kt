package com.example.buttsweetsfinal.network

import androidx.appcompat.app.AppCompatActivity
import com.example.buttsweetsfinal.enums.DialogTheme
import com.example.buttsweetsfinal.providers.AlertDialogProvider
import com.example.buttsweetsfinal.providers.ProgressDialogProvider


abstract class BaseActivity : AppCompatActivity(), ApiCallbacks {

    override fun doBeforeApiCall() {
//        ProgressDialogProvider.show(this)
    }

    override fun doAfterApiCall() {
//        ProgressDialogProvider.dismiss()
    }

    override fun onApiFailure(errorCode: Int) {
        AlertDialogProvider.showFailureDialog(this, DialogTheme.ThemeWhite)
    }

    override fun onApiSuccess(apiResponse: BaseApiResponse) {}
}