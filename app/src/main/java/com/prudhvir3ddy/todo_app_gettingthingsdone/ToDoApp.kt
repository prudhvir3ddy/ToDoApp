package com.prudhvir3ddy.todo_app_gettingthingsdone

import android.app.Application
import com.prudhvir3ddy.todo_app_gettingthingsdone.storage.db.ToDoDatabase

class ToDoApp : Application() {

  fun getToDoDb(): ToDoDatabase {
    return ToDoDatabase.getInstance(this)
  }
}