package com.prudhvir3ddy.todo_app_gettingthingsdone.view.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.prudhvir3ddy.todo_app_gettingthingsdone.R
import com.prudhvir3ddy.todo_app_gettingthingsdone.R.layout
import com.prudhvir3ddy.todo_app_gettingthingsdone.storage.SharedPrefs
import com.prudhvir3ddy.todo_app_gettingthingsdone.view.main.TasksActivity
import kotlinx.android.synthetic.main.activity_login.fullname_til
import kotlinx.android.synthetic.main.activity_login.login_btn
import org.koin.android.ext.android.inject

class LoginActivity : AppCompatActivity() {

  private val sharedPrefs: SharedPrefs by inject()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(layout.activity_login)

    addTextWatcher()

    login_btn.setOnClickListener {
      val fullname = fullname_til.editText?.text.toString()
      getStarted(fullname)
    }
  }

  private fun getStarted(fullname: String) {
    if (!fullname.isBlank()) {
      startActivity(Intent(this, TasksActivity::class.java))
      sharedPrefs.setLogin(true)
      sharedPrefs.setFullName(fullname)
      finish()
    } else {
      fullname_til.error = getString(R.string.please_enter_your_name)
    }
  }

  private fun addTextWatcher() {
    fullname_til.editText?.addTextChangedListener {
      fullname_til.error = ""
      if (it.isNullOrBlank())
        fullname_til.error = getString(R.string.please_enter_your_name)
    }
  }
}
