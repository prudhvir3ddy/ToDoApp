package com.prudhvir3ddy.todo_app_gettingthingsdone.view.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.prudhvir3ddy.todo_app_gettingthingsdone.storage.SharedPrefs
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
  private val sharedPrefs: SharedPrefs
) : ViewModel() {

  private val _loginState = MutableLiveData(false)
  val loginState: LiveData<Boolean> = _loginState

  fun getStarted(fullName: String) {
    sharedPrefs.setLogin(true)
    sharedPrefs.setFullName(fullName)
  }

  fun checkLogin() = sharedPrefs.getLogin()

}