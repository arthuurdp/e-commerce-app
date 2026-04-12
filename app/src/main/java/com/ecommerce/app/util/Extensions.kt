package com.ecommerce.app.util

import android.content.Context
import android.content.res.ColorStateList
import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.ecommerce.app.R
import com.google.android.material.textfield.TextInputLayout

fun View.show() { visibility = View.VISIBLE }
fun View.hide() { visibility = View.GONE }

fun Fragment.showToast(message: String) =
    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()

fun Fragment.hideKeyboard() {
    val imm = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    val view = activity?.currentFocus ?: view
    imm.hideSoftInputFromWindow(view?.windowToken, 0)
    view?.clearFocus()
}

fun <T : Parcelable> Bundle.getParcelableCompat(key: String, clazz: Class<T>): T? {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        getParcelable(key, clazz)
    } else {
        @Suppress("DEPRECATION")
        getParcelable(key)
    }
}

fun Double.toCurrency(): String = "R$ %.2f".format(this)

fun setFieldError(context: Context, layout: TextInputLayout, message: String?) {
    val hasError = message != null

    if (hasError) {
        layout.isErrorEnabled = true
        layout.error = message
    } else {
        layout.error = null
        layout.isErrorEnabled = false
    }

    val color = if (hasError) {
        ContextCompat.getColor(context, R.color.red)
    } else {
        ContextCompat.getColor(context, R.color.purple)
    }

    layout.setStartIconTintList(ColorStateList.valueOf(color))
}
