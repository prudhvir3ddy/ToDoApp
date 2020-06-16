package com.prudhvir3ddy.todo_app_gettingthingsdone.view.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.prudhvir3ddy.todo_app_gettingthingsdone.storage.SharedPrefs
import javax.inject.Inject

class LoginViewModel @Inject constructor(
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