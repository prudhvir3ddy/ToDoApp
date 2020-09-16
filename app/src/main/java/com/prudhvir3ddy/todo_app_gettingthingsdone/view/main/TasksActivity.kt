package com.prudhvir3ddy.todo_app_gettingthingsdone.view.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.prudhvir3ddy.todo_app_gettingthingsdone.R
import com.prudhvir3ddy.todo_app_gettingthingsdone.R.string
import com.prudhvir3ddy.todo_app_gettingthingsdone.databinding.ActivityTasksBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_tasks.noWorkIv
import kotlinx.android.synthetic.main.activity_tasks.tasksRv

@AndroidEntryPoint
class TasksActivity : AppCompatActivity() {

  private lateinit var adapter: ToDoListAdapter

  private val viewModel: TasksViewModel by viewModels()

  private lateinit var binding: ActivityTasksBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityTasksBinding.inflate(layoutInflater).apply {
      viewmodel = viewModel
      lifecycleOwner = this@TasksActivity
    }
    setContentView(binding.root)

    Glide.with(this).load(R.drawable.add_task).into(noWorkIv)
    setTitle()

    binding.addTaskFab.setOnClickListener {
      setUpBottomDialog()
    }
    setUpRecyclerView()
    viewModel.setUpWorkManager()

  }

  private fun setUpRecyclerView() {

    binding.viewmodel?.let {
      adapter =
        ToDoListAdapter(viewModel = it)
      binding.tasksRv.adapter = adapter
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

  private fun setUpBottomDialog() {
    binding.viewmodel?.let {
      val bottomSheetDialog =
        BottomSheetDialog(it)
      bottomSheetDialog.show(supportFragmentManager, "ADD_TASK")
    }
  }

  private fun setTitle() {
    binding.welcomeTv.text = String.format(getString(string.welcome), viewModel.getFirstName())
  }

}
