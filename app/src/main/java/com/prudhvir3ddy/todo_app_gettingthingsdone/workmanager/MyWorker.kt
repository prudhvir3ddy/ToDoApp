package com.prudhvir3ddy.todo_app_gettingthingsdone.workmanager

import android.content.Context
import androidx.hilt.Assisted
import androidx.hilt.work.WorkerInject
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.prudhvir3ddy.todo_app_gettingthingsdone.storage.db.ToDoDatabase
import dagger.hilt.android.qualifiers.ApplicationContext

class MyWorker @WorkerInject constructor(
  private val toDoDatabase: ToDoDatabase,
  @Assisted @ApplicationContext context: Context,
  @Assisted workerParameters: WorkerParameters
) : CoroutineWorker(context, workerParameters) {

  override suspend fun doWork(): Result {
    toDoDatabase.todoDao().deleteCompleted(true)
    return Result.success()
  }
}
