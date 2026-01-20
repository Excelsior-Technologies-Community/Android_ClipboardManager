package com.ext.android_clipboardmanager

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.ext.clipboard.ClipboardHistory
import com.ext.clipboard.ClipboardKit

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 🔹 Views
        val etInput = findViewById<EditText>(R.id.etInput)
        val btnCopy = findViewById<Button>(R.id.btnCopy)
        val btnPaste = findViewById<Button>(R.id.btnPaste)
        val tvResult = findViewById<TextView>(R.id.tvResult)

        // 1️⃣ Init clipboard library
        ClipboardKit.init(this)

        // 2️⃣ Observe clipboard changes
        ClipboardKit.observe { text ->
            Log.d("ClipboardObserver", "Clipboard changed: $text")
        }

        // 3️⃣ Copy button
        btnCopy.setOnClickListener {
            val text = etInput.text.toString()
            ClipboardKit.copy(text)
            Log.d("ClipboardAction", "Copied: $text")
        }

        // 4️⃣ Paste button
        btnPaste.setOnClickListener {
            val pastedText = ClipboardKit.paste()
            tvResult.text = pastedText ?: "Clipboard is empty"

            // 5️⃣ Print history
            ClipboardHistory.getAll().forEachIndexed { index, value ->
                Log.d("ClipboardHistory", "$index → $value")
            }
        }
    }
}
