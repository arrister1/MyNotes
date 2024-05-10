package com.example.mynotes.helper

import android.content.Context
import android.content.SharedPreferences

class UserSharedPreference(context: Context) {
    private val PREF_NAME = "shareUserPreference"
    private val sharedPref: SharedPreferences
    val editor: SharedPreferences.Editor

    init{
        sharedPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        editor = sharedPref.edit()
    }

    fun put(key: String, value: String) {
        editor.putString(key, value)
            .apply()
    }

    fun getString(key: String) : String? {
        return sharedPref.getString(key, null)
    }

    fun put(key: String, value: Boolean) {
        editor.putBoolean(key, value)
            .apply()
    }

    fun getBoolean(key: String) : Boolean {
        return sharedPref.getBoolean(key, false)
    }

    fun clear() {
        editor.clear()
            .apply ()
    }










//
//    val login = "login"
//    val myPref = "Main_Pref"
//    val sharedPreference: SharedPreferences
//
//    init {
//        sharedPreference = activity.getSharedPreferences(myPref, Context.MODE_PRIVATE)
//    }
//
//    fun setStatusLogin(status: Boolean) {
//        sharedPreference.edit().putBoolean(login, status).apply()
//    }
//
//    fun getStatusLogin(): Boolean {
//        return sharedPreference.getBoolean(login, false)
//    }
//

}