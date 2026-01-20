## ClipboardKit
[![Kotlin](https://img.shields.io/badge/Kotlin-1.9-blue?logo=kotlin&logoColor=white)](https://kotlinlang.org/)
[![License: MIT](https://img.shields.io/badge/License-MIT-green)](LICENSE)
[![API](https://img.shields.io/badge/API-24%2B-orange)](#)
---

ClipboardKit is a lightweight, modern, and easy-to-use Android Clipboard Manager library written in Kotlin.
It simplifies clipboard operations such as copy, paste, observing clipboard changes, and maintaining clipboard history, while handling common Android pitfalls.

---

### Features

- Simple copy & paste API
- Observe clipboard changes in real time
- Automatic clipboard history tracking
- Clear clipboard support
- Safe initialization checks
- Compatible with modern Android versions
- Lightweight & fast
- Easy to integrate into any Android app

---

## Installation (JitPack)

### 1️⃣ Add JitPack to your **root `settings.gradle` or `build.gradle`**

```gradle
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven { url 'https://jitpack.io' }
    }
}
```
### Add Dependency
```
dependencies {
    implementation("com.github.Excelsior-Technologies-Community:Android_ExpandableTextView:1.0.1")
}
```

---

### Basic Usage

- Initialize ClipboardKit
You must initialize the library before using it.
```kotlin
ClipboardKit.init(context)
```

Copy Text to Clipboard
```kotlin
ClipboardKit.copy("Hello Clipboard")
```

Paste Text from Clipboard
```kotlin
val text = ClipboardKit.paste()
```

Clipboard History
```kotlin
val history = ClipboardHistory.getAll()
history.forEach {
    Log.d("ClipboardHistory", it)
}
```

### Example Usage

XML
```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:id="@+id/main"
    android:background="@color/white"
    android:padding="16dp"
    tools:context=".MainActivity">

    <!-- Input text -->
    <EditText
        android:id="@+id/etInput"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:hint="Enter text to copy"
        android:textColor="@color/black"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent"/>

    <!-- Copy button -->
    <Button
        android:id="@+id/btnCopy"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:text="Copy to Clipboard"
        app:layout_constraintTop_toBottomOf="@id/etInput"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        android:layout_marginTop="16dp"/>

    <!-- Paste button -->
    <Button
        android:id="@+id/btnPaste"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:text="Paste from Clipboard"
        app:layout_constraintTop_toBottomOf="@id/btnCopy"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        android:layout_marginTop="8dp"/>

    <!-- Output text -->
    <TextView
        android:id="@+id/tvResult"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:text="Pasted text will appear here"
        android:textSize="16sp"
        android:layout_marginTop="24dp"
        app:layout_constraintTop_toBottomOf="@id/btnPaste"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent"/>

</androidx.constraintlayout.widget.ConstraintLayout>
```

Kotlin
```kotlin

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
```

---

### License

```
MIT License

Copyright (c) 2025 Excelsior Technologies 

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```

