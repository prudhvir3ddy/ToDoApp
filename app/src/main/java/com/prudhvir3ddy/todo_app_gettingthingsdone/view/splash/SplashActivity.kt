package com.prudhvir3ddy.todo_app_gettingthingsdone.view.splash

import android.app.Activity
import android.content.Intent
import android.content.IntentSender.SendIntentException
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.snackbar.Snackbar
import com.google.android.play.core.appupdate.AppUpdateManager
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.install.InstallState
import com.google.android.play.core.install.InstallStateUpdatedListener
import com.google.android.play.core.install.model.AppUpdateType.IMMEDIATE
import com.google.android.play.core.install.model.InstallStatus
import com.google.android.play.core.install.model.UpdateAvailability
import com.google.firebase.iid.FirebaseInstanceId
import com.prudhvir3ddy.todo_app_gettingthingsdone.R.id
import com.prudhvir3ddy.todo_app_gettingthingsdone.R.layout
import com.prudhvir3ddy.todo_app_gettingthingsdone.ToDoApp
import com.prudhvir3ddy.todo_app_gettingthingsdone.storage.SharedPrefs
import com.prudhvir3ddy.todo_app_gettingthingsdone.view.login.LoginActivity
import com.prudhvir3ddy.todo_app_gettingthingsdone.view.main.TasksActivity
import com.prudhvir3ddy.todo_app_gettingthingsdone.view.onboarding.OnBoardingActivity
import javax.inject.Inject

class SplashActivity : AppCompatActivity() {
  val appUpdateManager: AppUpdateManager = AppUpdateManagerFactory.create(this)
  val appUpdateInfoTask = appUpdateManager.appUpdateInfo

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

  override fun onResume() {
    super.onResume()
    //check for update after retrieving token
    Handler().postDelayed(this::triggerUpdate, 1000)
  }

  private fun triggerUpdate() {
    appUpdateInfoTask.addOnSuccessListener { appUpdateInfo ->
      if (appUpdateInfo.installStatus() == InstallStatus.DOWNLOADED) {
        popSnackbarForUpdate()
      } else if (appUpdateInfo.updateAvailability() == UpdateAvailability.DEVELOPER_TRIGGERED_UPDATE_IN_PROGRESS) {
        try {
          appUpdateManager.startUpdateFlowForResult(
            appUpdateInfo,
            IMMEDIATE,
            this,
            1011
          )
        } catch (e: SendIntentException) {
          e.printStackTrace()
        }
      } else if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE && appUpdateInfo.isUpdateTypeAllowed(
          IMMEDIATE
        )
      ) {
        appUpdateManager.startUpdateFlowForResult(
          appUpdateInfo,
          IMMEDIATE,
          this,
          1001
        )
      }
    }
  }

  private val updatedListener: InstallStateUpdatedListener = object : InstallStateUpdatedListener {
    override fun onStateUpdate(installState: InstallState) {
      when {
        installState.installStatus() == InstallStatus.DOWNLOADED -> {
          popSnackbarForUpdate()
        }
        installState.installStatus() == InstallStatus.INSTALLED -> {
          appUpdateManager.unregisterListener(this)
        }
        else -> {
          Log.i(
            javaClass.simpleName,
            "InstallStateUpdatedListener: state: " + installState.installStatus()
          )
        }
      }
    }
  }

  private fun popSnackbarForUpdate() {
    val snackbar: Snackbar = Snackbar.make(
      findViewById(id.splash_layout),
      "An update has just been downloaded! ",
      Snackbar.LENGTH_INDEFINITE
    )
    snackbar.setAction(
      "INSTALL"
    ) { appUpdateManager.completeUpdate() }
    snackbar.setActionTextColor(Color.CYAN)
    snackbar.show()
  }

  override fun onActivityResult(
    requestCode: Int,
    resultCode: Int,
    data: Intent?
  ) {
    super.onActivityResult(requestCode, resultCode, data)
    if (requestCode == 1001) {
      if (resultCode != Activity.RESULT_OK) {
        Log.e(javaClass.simpleName, "onActivityResult: App download failed")
      }
    }
  }

}