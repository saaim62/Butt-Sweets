package com.example.buttsweetsfinal.providers

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.example.buttsweetsfinal.R
import com.example.buttsweetsfinal.enums.DialogTheme
import com.example.buttsweetsfinal.listeners.DialogButtonClickListener


@SuppressLint("StaticFieldLeak")
object AlertDialogProvider {

    private var negativeButton: TextView? = null
    private var positiveButton: Button? = null
    private var textViewTitle: TextView? = null
    private var textViewMessage: TextView? = null

    fun showFailureDialog(context: Context, theme: DialogTheme){
        val dialogView = getView(context, theme)
        initializeViews(dialogView)
        textViewTitle!!.text = context.getString(R.string.error)
        textViewMessage!!.text = context.getString(R.string.sorry_something_went_wrong)
        val alertDialog = createAlertDialog(context, dialogView)
        positiveButton!!.setOnClickListener { alertDialog.dismiss() }
    }

    fun showAlertDialog(context: Context, theme: DialogTheme, message: String?) {
        val dialogView = getView(context, theme)
        initializeViews(dialogView)
        textViewMessage!!.text = message
        val alertDialog = createAlertDialog(context, dialogView)
        positiveButton!!.setOnClickListener { alertDialog.dismiss() }
    }

    fun showAlertDialog(context: Context, theme: DialogTheme, message: String?, positiveBtnText: String?, dialogButtonClickListener: DialogButtonClickListener) {
        val dialogView = getView(context, theme)
        initializeViews(dialogView)
        textViewMessage!!.text = message
        positiveButton!!.text = positiveBtnText
        val alertDialog = createAlertDialog(context, dialogView)
        positiveButton!!.setOnClickListener { dialogButtonClickListener.onClick(alertDialog) }
    }

    fun showAlertDialog(context: Context, theme: DialogTheme, message: String?, positiveBtnText: String?, positiveClickListener: DialogButtonClickListener,
                        negativeClickListener: DialogButtonClickListener) {
        val dialogView = getView(context, theme)
        initializeViews(dialogView)
        textViewMessage!!.text = message
        positiveButton!!.text = positiveBtnText
        negativeButton!!.visibility = View.VISIBLE
        val alertDialog = createAlertDialog(context, dialogView)
        positiveButton!!.setOnClickListener { positiveClickListener.onClick(alertDialog) }
        negativeButton!!.setOnClickListener { negativeClickListener.onClick(alertDialog) }
    }

    private fun getView(context: Context, theme: DialogTheme) : View {
        var layoutView: View? = null
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        when (theme) {
            DialogTheme.ThemeGreen -> layoutView = inflater.inflate(R.layout.layout_alert_dialog_green, null)
            DialogTheme.ThemeWhite -> layoutView = inflater.inflate(R.layout.layout_alert_dialog_white, null)
        }

        return layoutView
    }

    private fun initializeViews(dialogView: View) {
        negativeButton = dialogView.findViewById(R.id.negativeBtn)
        positiveButton = dialogView.findViewById(R.id.positiveBtn)
        textViewTitle = dialogView.findViewById(R.id.textView_title)
        textViewMessage = dialogView.findViewById(R.id.textView_message)
    }

    private fun createAlertDialog(context: Context, dialogView: View) : AlertDialog {
        val alertDialog = AlertDialog.Builder(context).setView(dialogView).create()
        alertDialog.window?.attributes?.windowAnimations = R.style.DialogAnimation
        alertDialog.setCancelable(false)
        alertDialog.show()

        alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return alertDialog
    }
}