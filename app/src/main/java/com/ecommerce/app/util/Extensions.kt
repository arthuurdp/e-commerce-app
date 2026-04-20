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
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ecommerce.app.BuildConfig
import com.ecommerce.app.R
import com.google.android.material.textfield.TextInputLayout
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

fun View.show() { visibility = View.VISIBLE }
fun View.hide() { visibility = View.GONE }

fun RecyclerView.addDivider() {
    val divider = DividerItemDecoration(context, LinearLayoutManager.VERTICAL)
    addItemDecoration(divider)
}

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

fun String?.toImageUrl(): String? {
    if (this.isNullOrBlank()) return null
    return if (this.startsWith("http")) {
        this
    } else {
        "${BuildConfig.BASE_URL}/uploads/$this"
    }
}

fun String?.formatDate(): String {
    if (this.isNullOrBlank()) return ""
    return try {
        val inputFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME
        val outputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy 'às' HH:mm", Locale("pt", "BR"))
        val parsed = LocalDateTime.parse(this, inputFormatter)
        parsed.format(outputFormatter)
    } catch (e: Exception) {
        this
    }
}

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
        ContextCompat.getColor(context, R.color.primary)
    }

    layout.setStartIconTintList(ColorStateList.valueOf(color))
}
