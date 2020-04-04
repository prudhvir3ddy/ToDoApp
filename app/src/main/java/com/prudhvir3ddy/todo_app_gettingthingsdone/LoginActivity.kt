package com.prudhvir3ddy.todo_app_gettingthingsdone

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.fullname_til
import kotlinx.android.synthetic.main.activity_login.login_btn
import kotlinx.android.synthetic.main.activity_login.username_til

class LoginActivity : AppCompatActivity() {

  lateinit var sharedPrefs: SharedPrefs

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_login)

    sharedPrefs = SharedPrefs(this)

    login_btn.setOnClickListener {
      val fullname = fullname_til.editText?.text.toString()
      val username = username_til.editText?.text.toString()
      if (!fullname.isBlank() && !username.isBlank()) {
        startActivity(Intent(this, TasksActivity::class.java))
        sharedPrefs.setLogin(true)
        sharedPrefs.setFullName(fullname)
        finish()
      } else {
        Toast.makeText(this, "please enter details", Toast.LENGTH_SHORT).show()
      }
    }
  }
}
