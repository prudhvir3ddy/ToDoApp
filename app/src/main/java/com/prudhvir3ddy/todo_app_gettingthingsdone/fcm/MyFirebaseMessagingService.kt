package com.prudhvir3ddy.todo_app_gettingthingsdone.fcm

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.prudhvir3ddy.todo_app_gettingthingsdone.R

class MyFirebaseMessagingService : FirebaseMessagingService() {

  val TAG = "FIREBASE_MESSAGE"

  override fun onMessageReceived(message: RemoteMessage) {
    super.onMessageReceived(message)
    Log.d(TAG, message.from.toString())
    Log.d(TAG, message.data.toString())
    setUpNotification(message.notification?.title, message.notification?.body)

  }

  private fun setUpNotification(title: String?, body: String?) {
    val channelId = "ToDo ID"
    val ringtone = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
    val notificationBuilder = NotificationCompat.Builder(this, channelId)
      .setSmallIcon(R.mipmap.ic_launcher_round)
      .setSound(ringtone)
      .setContentTitle(title)
      .setContentText(body)
    val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) {
      val channel = NotificationChannel(channelId, "todo", NotificationManager.IMPORTANCE_HIGH)
      notificationManager.createNotificationChannel(channel)
    }
    notificationManager.notify(0, notificationBuilder.build())
  }

  override fun onNewToken(p0: String?) {
    super.onNewToken(p0)
    Log.d(TAG, p0 ?: "No New Token Found")
  }
}