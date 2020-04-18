package com.prudhvir3ddy.todo_app_gettingthingsdone.workmanager

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.prudhvir3ddy.todo_app_gettingthingsdone.storage.db.ToDoDatabase
import org.koin.core.KoinComponent
import org.koin.core.inject

class MyWorker(
  val context: Context,
  workerParameters: WorkerParameters
) : Worker(context, workerParameters), KoinComponent {

  override fun doWork(): Result {
    val todoDatabase: ToDoDatabase by inject()
    todoDatabase.databaseWriteExecutor.execute {
      todoDatabase.todoDao().deleteCompleted(true)
    }
    return Result.success()
  }
}
