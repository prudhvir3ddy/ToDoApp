package com.prudhvir3ddy.todo_app_gettingthingsdone

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {

  lateinit var sharedPrefs: SharedPrefs

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_splash)

    sharedPrefs = SharedPrefs(this)

    if (sharedPrefs.getLogin()) {
      startActivity(Intent(this, TasksActivity::class.java))
    } else {
      startActivity(Intent(this, LoginActivity::class.java))
    }
    finish()
  }
}
