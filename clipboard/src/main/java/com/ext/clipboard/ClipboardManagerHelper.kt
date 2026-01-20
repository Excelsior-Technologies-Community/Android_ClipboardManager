package com.ext.clipboard

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context

class ClipboardManagerHelper(private val context: Context) {

    private val clipboardManager: ClipboardManager =
        context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

    fun copyText(label: String, text: String) {
        val clip = ClipData.newPlainText(label, text)
        clipboardManager.setPrimaryClip(clip)
    }

    fun getCopiedText(): String? {
        val clipData = clipboardManager.primaryClip
        return if (clipData != null && clipData.itemCount > 0) {
            clipData.getItemAt(0).text?.toString()
        } else null
    }

    fun hasText(): Boolean {
        return clipboardManager.hasPrimaryClip()
    }
}
