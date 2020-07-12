package com.example.developernotebook.util

import android.content.Context
import android.content.SharedPreferences

/**
 * This is a wrapper class for shared preferences
 * */

class Prefs(context: Context) {

    companion object {
        const val FIRST_NAME = "first_name"
        const val LAST_NAME = "last_name"
        const val EMAIL_ADDRESS = "email_address"
        const val PHONE_NUMBER = "phone_number"
        const val USER_NAME = "user_name"
        const val PASSWORD = "password"
        const val PREFS_FILENAME = "com.example.developernotebook.util.usermanager"
    }

    val prefs: SharedPreferences = context.getSharedPreferences(PREFS_FILENAME, 0)

    var firstName: String?
        get() = prefs.getString(FIRST_NAME,"")
        set(value) = prefs.edit().put(FIRST_NAME to value.toString())

    var lastName: String?
        get() = prefs.getString(LAST_NAME,"")
        set(value) = prefs.edit().put(LAST_NAME to value.toString())

    var emailAddress: String?
        get() = prefs.getString(EMAIL_ADDRESS,"")
        set(value) = prefs.edit().put(EMAIL_ADDRESS to value.toString())

    var phoneNumber: String?
        get() = prefs.getString(PHONE_NUMBER,"")
        set(value) = prefs.edit().put(PHONE_NUMBER to value.toString())

    var userName: String?
        get() = prefs.getString(USER_NAME,"")
        set(value) = prefs.edit().put(USER_NAME to value.toString())

    var password: String?
        get() = prefs.getString(PASSWORD,"")
        set(value) = prefs.edit().put(PASSWORD to value.toString())
}