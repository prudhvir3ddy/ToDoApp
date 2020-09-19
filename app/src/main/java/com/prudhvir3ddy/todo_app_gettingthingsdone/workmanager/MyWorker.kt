package com.prudhvir3ddy.todo_app_gettingthingsdone.workmanager

import android.app.NotificationManager
import android.content.Context
import androidx.core.content.ContextCompat
import androidx.hilt.Assisted
import androidx.hilt.work.WorkerInject
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.prudhvir3ddy.todo_app_gettingthingsdone.R
import com.prudhvir3ddy.todo_app_gettingthingsdone.storage.db.ToDoDatabase
import com.prudhvir3ddy.todo_app_gettingthingsdone.utils.sendNotification
import dagger.hilt.android.qualifiers.ApplicationContext

class MyWorker @WorkerInject constructor(
  private val toDoDatabase: ToDoDatabase,
  @Assisted @ApplicationContext val context: Context,
  @Assisted workerParameters: WorkerParameters
) : CoroutineWorker(context, workerParameters) {

  override suspend fun doWork(): Result {
    // toDoDatabase.todoDao().deleteCompleted(true)
    val notificationManager = ContextCompat.getSystemService(
      context,
      NotificationManager::class.java
    ) as NotificationManager
    notificationManager.sendNotification(
      context.getString(R.string.remember_why_you_started_desc),
      context
    )
    return Result.success()
  }
}
