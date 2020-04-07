package com.prudhvir3ddy.todo_app_gettingthingsdone.view.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.prudhvir3ddy.todo_app_gettingthingsdone.R.layout
import com.prudhvir3ddy.todo_app_gettingthingsdone.storage.SharedPrefs

class SplashActivity : AppCompatActivity() {

  lateinit var sharedPrefs: SharedPrefs

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(layout.activity_splash)

    sharedPrefs =
      SharedPrefs(this)

    if (sharedPrefs.getLogin()) {
      startActivity(Intent(this, TasksActivity::class.java))
    } else {
      startActivity(Intent(this, LoginActivity::class.java))
    }
    finish()
  }
}
