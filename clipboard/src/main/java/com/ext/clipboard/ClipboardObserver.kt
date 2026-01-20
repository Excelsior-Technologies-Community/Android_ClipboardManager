package com.ext.clipboard

fun interface ClipboardObserver {
    fun onChanged(text: String?)
}