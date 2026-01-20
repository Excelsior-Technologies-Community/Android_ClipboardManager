package com.ext.clipboard

object ClipboardHistory {
    private val history = mutableListOf<String>()

    fun add(text: String) {
        if (text.isNotEmpty()) history.add(0, text)
        if (history.size > 20) history.removeLast()
    }

    fun getAll(): List<String> = history
}
