package com.example.buttsweetsfinal.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.buttsweetsfinal.R
import com.example.buttsweetsfinal.adapters.ProductsAdapter
import com.example.buttsweetsfinal.base.models.AccountRepo
import com.example.buttsweetsfinal.base.modules.accountRepoModule
import com.example.buttsweetsfinal.base.viewmodels.BaseViewModel
import com.example.buttsweetsfinal.enums.DialogTheme
import com.example.buttsweetsfinal.network.BaseApiResponse
import com.example.buttsweetsfinal.network.HttpErrorCodes
import com.example.buttsweetsfinal.network.Product
import com.example.buttsweetsfinal.providers.AlertDialogProvider
import io.reactivex.Notification
import kotlinx.android.synthetic.main.activity_products.*

class ActivityProductViewModel(private val accountRepo: AccountRepo) : BaseViewModel(accountRepo) {
    val observable: MutableLiveData<Notification<List<Product>>> = MutableLiveData()

    fun getProducts(categoryId: String?) {
        categoryId.let { accountRepo.getProducts(this, categoryId = it)}
    }
    override fun onApiSuccess(apiResponse: BaseApiResponse) {
        super.onApiSuccess(apiResponse)

        if (apiResponse.collection.isNotEmpty()) {
            when (apiResponse.collection[0]) {
                is Product -> {
                    val products = apiResponse.collection as List<Product>
                    observable.postValue(Notification.createOnNext(products))
                }
            }
        }
    }

    override fun onApiFailure(errorCode: Int) {
        super.onApiFailure(errorCode)
        if (errorCode == HttpErrorCodes.Unauthorized.code) {
//            AlertDialogProvider.showAlertDialog(
//                this,
//                DialogTheme.ThemeWhite,
//                getString(R.string.response_fail)
//            )
            observable.postValue(Notification.createOnError(Throwable(errorCode.toString())))
        } else {
            super.onApiFailure(errorCode)
        }
    }
}