package com.prudhvir3ddy.todo_app_gettingthingsdone.view.splash

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId
import com.prudhvir3ddy.todo_app_gettingthingsdone.R.layout
import com.prudhvir3ddy.todo_app_gettingthingsdone.ToDoApp
import com.prudhvir3ddy.todo_app_gettingthingsdone.storage.SharedPrefs
import com.prudhvir3ddy.todo_app_gettingthingsdone.view.login.LoginActivity
import com.prudhvir3ddy.todo_app_gettingthingsdone.view.main.TasksActivity
import com.prudhvir3ddy.todo_app_gettingthingsdone.view.onboarding.OnBoardingActivity
import javax.inject.Inject

class SplashActivity : AppCompatActivity() {

  @Inject
  lateinit var sharedPrefs: SharedPrefs
  val TAG = SplashActivity::class.simpleName

  override fun onCreate(savedInstanceState: Bundle?) {
    (application as ToDoApp).appComponent.inject(this)
    super.onCreate(savedInstanceState)
    setContentView(layout.activity_splash)

    getFcmToken()
    when {
      sharedPrefs.getLogin() -> {
        startActivity(Intent(this, TasksActivity::class.java))
      }
      sharedPrefs.getFirstTime() -> {
        startActivity(Intent(this, LoginActivity::class.java))
      }
      else -> {
        startActivity(Intent(this, OnBoardingActivity::class.java))
      }
    }
    finish()
  }

  private fun getFcmToken() {
    FirebaseInstanceId.getInstance().instanceId
      .addOnCompleteListener(OnCompleteListener { task ->
        if (!task.isSuccessful) {
          Log.w(TAG, "getInstanceId failed", task.exception)
          return@OnCompleteListener
        }
        // Get new Instance ID token
        val token = task.result!!.token
        // Log and toast
        Log.d(TAG, token)
      })

  }
}
