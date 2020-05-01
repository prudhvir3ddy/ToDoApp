package com.prudhvir3ddy.todo_app_gettingthingsdone

import android.app.Application
import com.prudhvir3ddy.todo_app_gettingthingsdone.di.AppComponent
import com.prudhvir3ddy.todo_app_gettingthingsdone.di.DaggerAppComponent


class ToDoApp : Application() {

  val appComponent: AppComponent by lazy {
    DaggerAppComponent.factory()
      .create(applicationContext)
  }

}