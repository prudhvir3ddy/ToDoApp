package com.prudhvir3ddy.todo_app_gettingthingsdone.workers

import android.app.NotificationManager
import android.content.Context
import androidx.core.content.ContextCompat
import androidx.hilt.work.HiltWorker
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.prudhvir3ddy.todo_app_gettingthingsdone.R
import com.prudhvir3ddy.todo_app_gettingthingsdone.utils.sendNotification
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import java.util.Calendar
import java.util.concurrent.TimeUnit.MILLISECONDS

@HiltWorker
class NotificationWorker @AssistedInject constructor(
  @Assisted val context: Context,
  @Assisted workerParameters: WorkerParameters
) : Worker(context, workerParameters) {

  override fun doWork(): Result {

    val notificationManager = ContextCompat.getSystemService(
      context,
      NotificationManager::class.java
    ) as NotificationManager

    notificationManager.sendNotification(
      context.getString(R.string.remember_why_you_started_desc),
      context
    )

    WorkManager.getInstance(context).enqueue(getWorkManagerRequest())

    return Result.success()
  }

  companion object {

    fun getWorkManagerRequest(): OneTimeWorkRequest {

      val currentData = Calendar.getInstance()
      val dueDate = Calendar.getInstance()

      dueDate.set(Calendar.HOUR_OF_DAY, 15)
      dueDate.set(Calendar.MINUTE, 8)
      dueDate.set(Calendar.SECOND, 0)

      if (dueDate.before(currentData)) {
        dueDate.add(Calendar.HOUR_OF_DAY, 24)
      }

      val timeDiff = dueDate.timeInMillis - currentData.timeInMillis

      return OneTimeWorkRequestBuilder<NotificationWorker>()
        .addTag("BOO")
        .setInitialDelay(timeDiff, MILLISECONDS)
        .build()
    }

  }
}
