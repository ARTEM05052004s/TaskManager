package com.example.taskmanager.data.local

import android.content.Context
import android.content.Context.MODE_PRIVATE

class Pref(private val context: Context) {

    private val pref by lazy {
        context.getSharedPreferences(PREF_NAME, MODE_PRIVATE)
    }

    fun isUserSeen(): Boolean {
        return pref.getBoolean(SEEN_KEY, false)
    }

    fun saveSeen() {
        pref.edit().putBoolean(SEEN_KEY, true).apply()
    }

    fun saveUserName(name: String) {
        pref.edit().putString(USER_NAME, name).apply()
    }

    fun getName(): String? {
        return pref.getString(USER_NAME, "")
    }

    fun saveImage(image: String) {
        pref.edit().putString(USER_IMAGE, image).apply()
    }

    fun getImage(): String {
        return pref.getString(USER_IMAGE, "").toString()
    }

    companion object {
        const val PREF_NAME = "task.pref"
        const val SEEN_KEY = "user_key"
        const val USER_NAME = "user_name"
        const val USER_IMAGE = "user_image"
    }

}