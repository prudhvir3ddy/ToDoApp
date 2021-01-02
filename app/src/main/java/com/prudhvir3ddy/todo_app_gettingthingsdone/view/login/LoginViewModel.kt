package com.prudhvir3ddy.todo_app_gettingthingsdone.view.login

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.prudhvir3ddy.todo_app_gettingthingsdone.storage.SharedPrefs
import com.prudhvir3ddy.todo_app_gettingthingsdone.utils.Event

class LoginViewModel @ViewModelInject constructor(
  private val sharedPrefs: SharedPrefs
) : ViewModel() {

  private val _isLoggedIn = MutableLiveData<Event<Boolean>>()

  val isLoggedIn: LiveData<Event<Boolean>>
    get() = _isLoggedIn

  fun getStarted(fullName: String) {
    sharedPrefs.setLogin(true)
    sharedPrefs.setFullName(fullName)
    _isLoggedIn.value = Event(true)
  }

  fun checkLogin() {
    if (sharedPrefs.getLogin()) {
      _isLoggedIn.value = Event(true)
    }
  }

}