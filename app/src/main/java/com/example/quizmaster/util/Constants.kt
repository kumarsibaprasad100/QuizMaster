package com.example.quizmaster.util

import android.app.Activity
import android.content.Context

import android.content.SharedPreferences


class Constants {
    companion object {
        fun SetToken(token: String, activity: Activity) {
            val preferences: SharedPreferences =
                activity.getSharedPreferences("MY_APP", Context.MODE_PRIVATE)
            preferences.edit().putString("TOKEN", token).apply()

        }

        fun getToken(activity: Activity): String {
            val preferences: SharedPreferences =
                activity.getSharedPreferences("MY_APP", Context.MODE_PRIVATE)
            val retrivedToken = preferences.getString("TOKEN", null)
            return retrivedToken ?: ""
        }

        fun setLoggedIn(isLoggedIn: Boolean, activity: Activity) {
            val preferences: SharedPreferences =
                activity.getSharedPreferences("MY_APP", Context.MODE_PRIVATE)
            preferences.edit().putBoolean("IS_LOGGED_IN", isLoggedIn).apply()
        }

        fun getLoggedIn(activity: Activity): Boolean {
            val preferences: SharedPreferences =
                activity.getSharedPreferences("MY_APP", Context.MODE_PRIVATE)
            val isLoggedIn = preferences.getBoolean("IS_LOGGED_IN", false)
            return isLoggedIn
        }
    }
}