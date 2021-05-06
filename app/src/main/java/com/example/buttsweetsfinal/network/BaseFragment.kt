package com.example.buttsweetsfinal.network

import androidx.fragment.app.Fragment
import com.example.buttsweetsfinal.enums.DialogTheme
import com.example.buttsweetsfinal.providers.AlertDialogProvider
import com.example.buttsweetsfinal.providers.ProgressDialogProvider

abstract class BaseFragment : Fragment(), ApiCallbacks {

    override fun doBeforeApiCall() {
//        ProgressDialogProvider.show(requireActivity())
    }

    override fun doAfterApiCall() {
//        ProgressDialogProvider.dismiss()
    }

    override fun onApiFailure(errorCode: Int) {
        AlertDialogProvider.showFailureDialog(requireActivity(), DialogTheme.ThemeWhite)
    }

    override fun onApiSuccess(apiResponse: BaseApiResponse) {}
}