package com.prudhvir3ddy.todo_app_gettingthingsdone

import android.app.Application
import com.prudhvir3ddy.todo_app_gettingthingsdone.di.appModule
import com.prudhvir3ddy.todo_app_gettingthingsdone.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class ToDoApp : Application() {

  override fun onCreate() {
    super.onCreate()

    koinInit()

  }

  private fun koinInit() {
    startKoin {
      androidContext(this@ToDoApp)

      if (BuildConfig.DEBUG)
        androidLogger()

      modules(listOf(appModule, viewModelModule))
    }
  }

}