package com.ecommerce.app.util

import android.view.LayoutInflater
import com.ecommerce.app.databinding.DialogReviewOptionsBinding
import com.google.android.material.bottomsheet.BottomSheetDialog

object DialogUtils {

    fun <T> showOptionsDialog(
        context: android.content.Context,
        item: T,
        editLabel: String? = null,
        deleteLabel: String? = null,
        onEditClick: (T) -> Unit,
        onDeleteClick: (T) -> Unit
    ) {
        val dialog = BottomSheetDialog(context, com.google.android.material.R.style.Theme_Design_BottomSheetDialog)
        val dialogBinding = DialogReviewOptionsBinding.inflate(LayoutInflater.from(context))

        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        editLabel?.let { dialogBinding.btnEdit.text = it }
        deleteLabel?.let { dialogBinding.btnDelete.text = it }

        dialogBinding.btnEdit.setOnClickListener {
            onEditClick(item)
            dialog.dismiss()
        }
        dialogBinding.btnDelete.setOnClickListener {
            onDeleteClick(item)
            dialog.dismiss()
        }

        dialog.setContentView(dialogBinding.root)
        dialog.show()
    }
}
