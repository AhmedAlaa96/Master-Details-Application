package com.example.master_detailsapplication.infrastructure.utils

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.example.master_detailsapplication.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder

object Utils {

    fun hideKeyboard(context: Context, view: View){
        val inputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun noInternetConnectionDialog(activity: Activity) {
        MaterialAlertDialogBuilder(
            activity,
            R.style.Body_ThemeOverlay_MaterialComponents_MaterialAlertDialog
        )
            .setTitle(R.string.no_internet_connection_title)
            .setMessage(R.string.no_internet_connection)
            .setNegativeButton(R.string.ok_text) { dialog, _ ->
                dialog.dismiss()
            }
            .show()


    }

    fun errorCustomDialog(activity: Activity) {
        MaterialAlertDialogBuilder(
            activity,
            R.style.Body_ThemeOverlay_MaterialComponents_MaterialAlertDialog
        )
            .setTitle(R.string.error_msg_text_title)
            .setMessage(R.string.error_msg_text)
            .setNegativeButton(R.string.ok_text) { dialog, _ ->
                dialog.dismiss()
            }
            .show()


    }

}