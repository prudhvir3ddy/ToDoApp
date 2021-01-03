package com.prudhvir3ddy.todo_app_gettingthingsdone.view

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.prudhvir3ddy.todo_app_gettingthingsdone.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HostActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_host)
  }

  override fun onBackPressed() {
    val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
    if (Build.VERSION.SDK_INT == Build.VERSION_CODES.Q && isTaskRoot && navHostFragment?.childFragmentManager?.backStackEntryCount == 0) {
      finishAfterTransition()
    } else {
      super.onBackPressed()
    }
  }
}