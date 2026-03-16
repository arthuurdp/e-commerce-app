package com.ecommerce.app.util

import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

// ─── Visibility helpers ────────────────────────────────────────────────────

fun View.show() { visibility = View.VISIBLE }
fun View.hide() { visibility = View.GONE }
fun View.invisible() { visibility = View.INVISIBLE }

fun View.showIf(condition: Boolean) {
    visibility = if (condition) View.VISIBLE else View.GONE
}

// ─── Toast / Snackbar ─────────────────────────────────────────────────────

fun Fragment.showToast(message: String) =
    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()

fun Fragment.showLongToast(message: String) =
    Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()

fun View.showSnackbar(message: String, duration: Int = Snackbar.LENGTH_SHORT) =
    Snackbar.make(this, message, duration).show()

fun View.showSnackbarWithAction(
    message: String,
    actionText: String,
    action: () -> Unit
) = Snackbar.make(this, message, Snackbar.LENGTH_INDEFINITE)
    .setAction(actionText) { action() }
    .show()

// ─── Currency formatting ──────────────────────────────────────────────────

fun Double.toCurrency(): String = "R$ %.2f".format(this)

// ─── Lifecycle-aware coroutine helper ─────────────────────────────────────

fun Fragment.launchOnStarted(block: suspend () -> Unit) {
    viewLifecycleOwner.lifecycleScope.launch {
        viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            block()
        }
    }
}
