package com.ext.clipboard

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context

object ClipboardKit {

    private lateinit var clipboardManager: ClipboardManager

    fun init(context: Context) {
        clipboardManager =
            context.applicationContext.getSystemService(
                Context.CLIPBOARD_SERVICE
            ) as ClipboardManager
    }

    fun copy(text: String) {
        clipboardManager.setPrimaryClip(
            ClipData.newPlainText("clipboard", text)
        )
    }

    fun paste(): String? {
        return clipboardManager.primaryClip
            ?.getItemAt(0)
            ?.text
            ?.toString()
    }
}
