package com.prudhvir3ddy.todo_app_gettingthingsdone.workers

import android.content.Context
import android.util.Log
import androidx.test.platform.app.InstrumentationRegistry
import androidx.work.Configuration
import androidx.work.WorkInfo
import androidx.work.WorkManager
import androidx.work.testing.SynchronousExecutor
import androidx.work.testing.WorkManagerTestInitHelper
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test

class NotificationWorkerTest {

  lateinit var context: Context

  @Before
  fun setUp() {
    context = InstrumentationRegistry.getInstrumentation().targetContext
    val config = Configuration.Builder()
      .setMinimumLoggingLevel(Log.DEBUG)
      .setExecutor(SynchronousExecutor())
      .build()

    WorkManagerTestInitHelper.initializeTestWorkManager(context, config)
  }

  @Test
  fun testNotificationWorker() {
    val workManager = WorkManager.getInstance(context)
    val request = NotificationWorker.getWorkManagerRequest()

    workManager.enqueue(request).result.get()
    WorkManagerTestInitHelper.getTestDriver(context)?.setInitialDelayMet(request.id)

    val workInfo = workManager.getWorkInfoById(request.id).get()
    assertThat(workInfo.state, `is`(WorkInfo.State.SUCCEEDED))

  }

}