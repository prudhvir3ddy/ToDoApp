package com.prudhvir3ddy.todo_app_gettingthingsdone.storage

import android.content.Context
import android.content.SharedPreferences
import com.prudhvir3ddy.todo_app_gettingthingsdone.storage.SharedPrefs.PrefConstants.FULL_NAME
import com.prudhvir3ddy.todo_app_gettingthingsdone.storage.SharedPrefs.PrefConstants.IS_LOGGED_IN
import com.prudhvir3ddy.todo_app_gettingthingsdone.storage.SharedPrefs.PrefConstants.TODO_APP_PREFS

class SharedPrefs(context: Context) {

  object PrefConstants {
    const val TODO_APP_PREFS = "todoAppPrefs"
    const val IS_LOGGED_IN = "isLoggedIn"
    const val FULL_NAME = "firstName"
  }

  private val sharedPreferences: SharedPreferences =
    context.getSharedPreferences(TODO_APP_PREFS, Context.MODE_PRIVATE)

  private val editor: SharedPreferences.Editor = sharedPreferences.edit()

  fun setLogin(login: Boolean) {
    editor.putBoolean(IS_LOGGED_IN, login)
    editor.commit()
  }

  fun setFullName(fullName: String) {
    editor.putString(FULL_NAME, fullName)
    editor.commit()
  }

  fun getFullName(): String = sharedPreferences.getString(FULL_NAME, null) ?: "boo"

  fun getLogin(): Boolean = sharedPreferences.getBoolean(IS_LOGGED_IN, false)

}