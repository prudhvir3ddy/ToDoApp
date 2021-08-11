package com.prudhvir3ddy.todo_app_gettingthingsdone.utils

import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.navigation.NavDeepLinkBuilder
import com.prudhvir3ddy.todo_app_gettingthingsdone.R
import com.prudhvir3ddy.todo_app_gettingthingsdone.view.HostActivity

fun NotificationManager.sendNotification(messageBody: String, applicationContext: Context) {

  val NOTIFICATION_ID = 1

//   TODO set pending intent
//  val contentPendingIntent =
//    NavDeepLinkBuilder(applicationContext).setComponentName(HostActivity::class.java)
//      .setGraph(R.navigation.nav_graph)
//      .setDestination(R.id.tasksFragment)
//      .createPendingIntent()

  val builder = NotificationCompat.Builder(
    applicationContext, applicationContext.getString(R.string.daily_notifications_channel_id)
  ).setSmallIcon(R.drawable.ic_baseline_done_24)
    .setContentTitle(applicationContext.getString(R.string.remember_why_you_started))
    .setAutoCancel(true)
    .setContentText(messageBody)



  notify(NOTIFICATION_ID, builder.build())
}