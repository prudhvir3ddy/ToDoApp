package com.prudhvir3ddy.todo_app_gettingthingsdone.utils

import android.R.id
import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

fun hideKeyboard(activity: Activity) {
  val view: View = activity.findViewById(id.content)
  val imm: InputMethodManager =
    activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
  imm.hideSoftInputFromWindow(view.windowToken, 0)
}