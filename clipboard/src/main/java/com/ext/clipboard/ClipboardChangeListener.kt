package com.ext.clipboard

interface ClipboardChangeListener {
    fun onClipboardChanged(text: String?)
}