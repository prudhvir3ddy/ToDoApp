package com.prudhvir3ddy.todo_app_gettingthingsdone

import android.app.Application
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import timber.log.Timber.DebugTree
import javax.inject.Inject

@HiltAndroidApp
class ToDoApp : Application(), Configuration.Provider {

  @Inject @JvmField var workerFactory: HiltWorkerFactory? = null

  override fun onCreate() {
    super.onCreate()


    if (BuildConfig.DEBUG) {
      Timber.plant(DebugTree())
    }
  }

  override fun getWorkManagerConfiguration() =
    Configuration.Builder().setWorkerFactory(workerFactory!!).build()

}