package com.prudhvir3ddy.todo_app_gettingthingsdone.view.login

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.prudhvir3ddy.todo_app_gettingthingsdone.storage.SharedPrefs

class LoginViewModel @ViewModelInject constructor(
  private val sharedPrefs: SharedPrefs
) : ViewModel() {

  private val _isLoggedIn = MutableLiveData<Boolean>()

  val isLoggedIn: LiveData<Boolean>
    get() = _isLoggedIn

  fun getStarted(fullName: String) {
    sharedPrefs.setLogin(true)
    sharedPrefs.setFullName(fullName)
    _isLoggedIn.value = true
  }

}