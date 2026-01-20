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
        checkInit()
        clipboardManager.setPrimaryClip(
            ClipData.newPlainText("clipboard", text)
        )
    }

    fun paste(): String? {
        checkInit()
        return clipboardManager.primaryClip
            ?.getItemAt(0)
            ?.text
            ?.toString()
    }
    private fun checkInit() {
        if (!::clipboardManager.isInitialized) {
            throw IllegalStateException(
                "ClipboardKit is not initialized. Call ClipboardKit.init(context)"
            )
        }
    }

    fun observe(observer: ClipboardObserver) {
        checkInit()

        clipboardManager.addPrimaryClipChangedListener {

            val text = paste()

            text?.let { ClipboardHistory.add(it) }

            observer.onChanged(text)
        }
    }


    fun clear() {
        checkInit()
        clipboardManager.setPrimaryClip(
            ClipData.newPlainText("", "")
        )
    }
    fun pasteOrNull(): String? {
        checkInit()
        return try {
            paste()
        } catch (e: SecurityException) {
            null
        }
    }
    fun pasteIfAllowed(isForeground: Boolean): String? {
        if (!isForeground) return null
        return paste()
    }

    fun copyEncrypted(text: String, encrypt: (String) -> String) {
        copy(encrypt(text))
    }


}
