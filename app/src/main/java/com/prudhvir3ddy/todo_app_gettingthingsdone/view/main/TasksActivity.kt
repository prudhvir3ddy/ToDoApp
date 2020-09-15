package com.prudhvir3ddy.todo_app_gettingthingsdone.view.main

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView.VERTICAL
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
    binding = ActivityTasksBinding.inflate(layoutInflater)
    setContentView(binding.root)

    Glide.with(this).load(R.drawable.add_task).into(noWorkIv)
    setTitle()

    binding.addTaskFab.setOnClickListener {
      setUpBottomDialog()
    }
    setUpRecyclerView()
    viewModel.setUpWorkManager()

    viewModel.tasksList.observe(this, Observer {
      if (it.isNotEmpty()) {
        adapter.submitList(it)
        binding.tasksRv.visibility = View.VISIBLE
        binding.noWorkIv.visibility = View.INVISIBLE
      } else {
        binding.tasksRv.visibility = View.INVISIBLE
        binding.noWorkIv.visibility = View.VISIBLE
      }
    })

  }

  private fun setUpRecyclerView() {

    adapter =
      ToDoListAdapter(viewModel = viewModel)
    binding.tasksRv.adapter = adapter
    binding.tasksRv.addItemDecoration(DividerItemDecoration(tasksRv.context, VERTICAL))
  }

  private fun setUpBottomDialog() {
    val bottomSheetDialog =
      BottomSheetDialog(viewModel)
    bottomSheetDialog.show(supportFragmentManager, "ADD_TASK")
  }

  private fun setTitle() {
    binding.welcomeTv.text = String.format(getString(string.welcome), viewModel.getFirstName())
  }

}
