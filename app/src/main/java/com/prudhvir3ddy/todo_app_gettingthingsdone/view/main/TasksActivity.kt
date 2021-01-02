package com.prudhvir3ddy.todo_app_gettingthingsdone.view.main

import android.app.NotificationChannel
import android.app.NotificationManager
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.prudhvir3ddy.todo_app_gettingthingsdone.R
import com.prudhvir3ddy.todo_app_gettingthingsdone.R.string
import com.prudhvir3ddy.todo_app_gettingthingsdone.databinding.ActivityTasksBinding
import com.prudhvir3ddy.todo_app_gettingthingsdone.storage.db.ToDo
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_tasks.noWorkIv
import kotlinx.android.synthetic.main.activity_tasks.tasksRv

@AndroidEntryPoint
class TasksActivity : AppCompatActivity() {

  private val viewModel: TasksViewModel by viewModels()

  private lateinit var binding: ActivityTasksBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    initUi()

    viewModel.editTaskEvent.observe(this, Observer {
      setUpBottomDialog(BottomSheetTag.EDIT_TASK, it.peekContent())
    })

    createChannel(
      getString(string.daily_notifications_channel_id),
      getString(string.daily_notifications_channel_name)
    )
    viewModel.setUpWorkManager()

  }

  private fun initUi() {
    binding = DataBindingUtil.setContentView(this, R.layout.activity_tasks)
    binding.viewmodel = viewModel
    binding.lifecycleOwner = this@TasksActivity

    Glide.with(this).load(R.drawable.add_task).into(noWorkIv)
    setTitle()

    binding.addTaskFab.setOnClickListener {
      setUpBottomDialog(BottomSheetTag.ADD_TASK)
    }
    setUpRecyclerView()
  }

  private fun setUpRecyclerView() {

    binding.viewmodel?.let {
      binding.tasksRv.adapter =
        ToDoListAdapter(viewModel = it)
      binding.tasksRv.addItemDecoration(DividerItemDecoration(tasksRv.context, VERTICAL))

      val itemTouchCallback =
        object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT) {
          override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: ViewHolder,
            target: ViewHolder
          ): Boolean {
            return false
          }

          override fun onSwiped(viewHolder: ViewHolder, direction: Int) {
            viewModel.onTaskDelete(viewModel.tasksList.value?.get(viewHolder.adapterPosition)?.id)
          }

        }
      val itemTouchHelper = ItemTouchHelper(itemTouchCallback)
      itemTouchHelper.attachToRecyclerView(binding.tasksRv)
      binding.tasksRv.setHasFixedSize(true)
    }
  }

  private fun setUpBottomDialog(
    tag: String,
    toDo: ToDo = ToDo()
  ) {
    val bottomSheetDialog = BottomSheetDialog(viewModel, toDo)
    bottomSheetDialog.show(supportFragmentManager, tag)
  }

  private fun createChannel(channelId: String, channelName: String) {
    if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) {
      val notificationChannel =
        NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_LOW)
      notificationChannel.enableLights(true)
      notificationChannel.lightColor = Color.RED
      notificationChannel.enableVibration(true)
      notificationChannel.description = getString(string.remember_why_you_started_desc)

      val notificationManager = this.getSystemService(NotificationManager::class.java)
      notificationManager.createNotificationChannel(notificationChannel)
    }
  }

  private fun setTitle() {
    binding.welcomeTv.text = String.format(getString(string.welcome), viewModel.getFirstName())
  }

}
