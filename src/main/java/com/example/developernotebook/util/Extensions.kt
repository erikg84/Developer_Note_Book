package com.example.developernotebook.util

import android.content.SharedPreferences
import android.telephony.PhoneNumberUtils
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText


var TextInputEditText.value
    get() = this.text.toString()
    set(value) { this.setText(value) }

fun SharedPreferences.Editor.put(pair: Pair<String, Any>) {
    val key = pair.first
    val value = pair.second
    when(value) {
        is String -> putString(key, value).commit()
        is Int -> putInt(key, value).commit()
        is Boolean -> putBoolean(key, value).commit()
        is Long -> putLong(key, value).commit()
        is Float -> putFloat(key, value).commit()
        else -> error("Only primitive types can be stored in SharedPreferences")
    }
}
