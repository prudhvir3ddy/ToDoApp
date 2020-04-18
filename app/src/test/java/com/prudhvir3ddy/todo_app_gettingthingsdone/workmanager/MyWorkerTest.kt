package com.prudhvir3ddy.todo_app_gettingthingsdone.workmanager

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.util.concurrent.TimeUnit.MINUTES

class MyWorkerTest {
  private lateinit var targetContext: Context

  @Before
  fun setContext() {
    targetContext = ApplicationProvider.getApplicationContext()
  }

  @Test
  @Throws(Exception::class)
  fun testPeriodicWork() {
    val request = PeriodicWorkRequestBuilder<MyWorker>(15L, MINUTES)
      .build()
    val workManager = WorkManager.getInstance(targetContext)
    workManager.enqueue(request).result.get()
    val workInfo = workManager.getWorkInfoById(request.id).get()
    Assert.assertEquals(workInfo.state, (WorkInfo.State.ENQUEUED))
  }
}