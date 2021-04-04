package com.example.buttsweetsfinal.providers

import android.annotation.SuppressLint
import android.content.Context
import com.kaopiz.kprogresshud.KProgressHUD

object ProgressDialogProvider {

    @SuppressLint("StaticFieldLeak")
    private lateinit var progressHud: KProgressHUD

    fun show(context: Context) {
        progressHud = KProgressHUD.create(context)
            .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
            .setCancellable(false)
            .setAnimationSpeed(2)
            .setDimAmount(0.5f)
            .show()
    }

    fun dismiss() {
        progressHud.dismiss()
    }
}