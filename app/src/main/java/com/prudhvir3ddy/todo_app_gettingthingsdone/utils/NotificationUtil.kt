package com.prudhvir3ddy.todo_app_gettingthingsdone.utils

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.prudhvir3ddy.todo_app_gettingthingsdone.R
import com.prudhvir3ddy.todo_app_gettingthingsdone.view.main.TasksActivity

fun NotificationManager.sendNotification(messageBody: String, applicationContext: Context) {

  val NOTIFICATION_ID = 1

  val contentIntent = Intent(applicationContext, TasksActivity::class.java)
  val contentPendingIntent = PendingIntent.getActivity(
    applicationContext,
    NOTIFICATION_ID,
    contentIntent, 0
  )

  val builder = NotificationCompat.Builder(
    applicationContext, applicationContext.getString(R.string.daily_notifications_channel_id)
  ).setSmallIcon(R.drawable.ic_baseline_done_24)
    .setContentTitle(applicationContext.getString(R.string.remember_why_you_started))
    .setContentIntent(contentPendingIntent)
    .setAutoCancel(true)
    .setContentText(messageBody)



  notify(NOTIFICATION_ID, builder.build())
}