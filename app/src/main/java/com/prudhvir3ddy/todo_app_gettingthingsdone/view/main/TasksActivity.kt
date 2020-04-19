package com.prudhvir3ddy.todo_app_gettingthingsdone.view.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.bumptech.glide.Glide
import com.prudhvir3ddy.todo_app_gettingthingsdone.R
import com.prudhvir3ddy.todo_app_gettingthingsdone.R.layout
import com.prudhvir3ddy.todo_app_gettingthingsdone.R.string
import com.prudhvir3ddy.todo_app_gettingthingsdone.ToDoApp
import com.prudhvir3ddy.todo_app_gettingthingsdone.storage.db.ToDo
import com.prudhvir3ddy.todo_app_gettingthingsdone.utils.IntentConstants
import com.prudhvir3ddy.todo_app_gettingthingsdone.view.detail.DetailActivity
import com.prudhvir3ddy.todo_app_gettingthingsdone.view.main.BottomSheetDialog.BottomSheetListener
import com.prudhvir3ddy.todo_app_gettingthingsdone.viewmodels.TasksViewModel
import com.prudhvir3ddy.todo_app_gettingthingsdone.viewmodels.ViewModelFactory
import kotlinx.android.synthetic.main.activity_tasks.add_task_fab
import kotlinx.android.synthetic.main.activity_tasks.noWorkIv
import kotlinx.android.synthetic.main.activity_tasks.tasksRv
import kotlinx.android.synthetic.main.activity_tasks.welcome_tv
import javax.inject.Inject

class TasksActivity : AppCompatActivity(), BottomSheetListener {

  private lateinit var adapter: ToDoListAdapter

  @Inject
  lateinit var viewModelFactory: ViewModelFactory

  lateinit var viewModel: TasksViewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    (application as ToDoApp).appComponent.inject(this)
    super.onCreate(savedInstanceState)
    setContentView(layout.activity_tasks)
    viewModel = ViewModelProvider(this, viewModelFactory)[TasksViewModel::class.java]

    Glide.with(this).load(R.drawable.add_task).into(noWorkIv)
    setTitle()

    add_task_fab.setOnClickListener {
      setUpBottomDialog()
    }
    setUpRecyclerView()
    viewModel.getDataFromDb()
    viewModel.setUpWorkManager()

    viewModel.tasksList.observe(this, Observer {
      if (it.isNotEmpty()) {
        adapter.submitList(it)
        tasksRv.visibility = View.VISIBLE
        noWorkIv.visibility = View.INVISIBLE
      }
    })

  }

  private fun setUpRecyclerView() {
    val click = object :
      ItemClickListener {
      override fun onClick(todo: ToDo) {
        val intent = Intent(this@TasksActivity, DetailActivity::class.java)
        intent.putExtra(IntentConstants.TODO, todo)
        startActivity(intent)
      }

      override fun onUpdate(todo: ToDo) {
        viewModel.onTaskUpdate(todo)
      }
    }

    adapter =
      ToDoListAdapter(click)
    tasksRv.adapter = adapter
    tasksRv.addItemDecoration(DividerItemDecoration(tasksRv.context, VERTICAL))
  }

  private fun setUpBottomDialog() {
    val bottomSheetDialog =
      BottomSheetDialog()
    bottomSheetDialog.show(supportFragmentManager, "ADD_TASK")
  }

  private fun setTitle() {
    welcome_tv.text = String.format(getString(string.welcome), viewModel.getFullName())
  }

  override fun onSave(taskName: String, taskDesc: String) {
    viewModel.onTaskSave(taskName, taskDesc)
    adapter.notifyDataSetChanged()

  }
}
