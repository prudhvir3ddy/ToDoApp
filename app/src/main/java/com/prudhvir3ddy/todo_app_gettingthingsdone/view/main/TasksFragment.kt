package com.prudhvir3ddy.todo_app_gettingthingsdone.view.main

import android.app.NotificationChannel
import android.app.NotificationManager
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.prudhvir3ddy.todo_app_gettingthingsdone.R
import com.prudhvir3ddy.todo_app_gettingthingsdone.R.string
import com.prudhvir3ddy.todo_app_gettingthingsdone.databinding.FragmentTasksBinding
import com.prudhvir3ddy.todo_app_gettingthingsdone.storage.db.ToDo
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TasksFragment : Fragment(R.layout.fragment_tasks) {

  private val viewModel: TasksViewModel by viewModels()

  private lateinit var binding: FragmentTasksBinding

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    initUi(view)

  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)



    viewModel.editTaskEvent.observe(this, Observer {
      setUpBottomDialog(BottomSheetTag.EDIT_TASK, it.peekContent())
    })

    createChannel(
      getString(string.daily_notifications_channel_id),
      getString(string.daily_notifications_channel_name)
    )
    viewModel.setUpWorkManager()

  }

  private fun initUi(view: View) {
    binding = FragmentTasksBinding.bind(view)
    binding.viewmodel = viewModel
    binding.lifecycleOwner = this@TasksFragment

    Glide.with(this).load(R.drawable.add_task).into(binding.noWorkIv)
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
      binding.tasksRv.addItemDecoration(DividerItemDecoration(binding.tasksRv.context, VERTICAL))

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
    bottomSheetDialog.show(childFragmentManager, tag)
  }

  private fun createChannel(channelId: String, channelName: String) {
    if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) {
      val notificationChannel =
        NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_LOW)
      notificationChannel.enableLights(true)
      notificationChannel.lightColor = Color.RED
      notificationChannel.enableVibration(true)
      notificationChannel.description = getString(string.remember_why_you_started_desc)

      val notificationManager = requireContext().getSystemService(NotificationManager::class.java)
      notificationManager.createNotificationChannel(notificationChannel)
    }
  }

  private fun setTitle() {
    binding.welcomeTv.text = String.format(getString(string.welcome), viewModel.getFirstName())
  }

}
