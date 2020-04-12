package com.prudhvir3ddy.todo_app_gettingthingsdone.view.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.prudhvir3ddy.todo_app_gettingthingsdone.R.layout
import com.prudhvir3ddy.todo_app_gettingthingsdone.R.string
import com.prudhvir3ddy.todo_app_gettingthingsdone.storage.db.ToDo
import com.prudhvir3ddy.todo_app_gettingthingsdone.utils.IntentConstants
import com.prudhvir3ddy.todo_app_gettingthingsdone.view.BottomSheetDialog
import com.prudhvir3ddy.todo_app_gettingthingsdone.view.BottomSheetDialog.BottomSheetListener
import com.prudhvir3ddy.todo_app_gettingthingsdone.view.ItemClickListener
import com.prudhvir3ddy.todo_app_gettingthingsdone.view.ToDoListAdapter
import com.prudhvir3ddy.todo_app_gettingthingsdone.viewmodels.TasksViewModel
import kotlinx.android.synthetic.main.activity_tasks.add_task_fab
import kotlinx.android.synthetic.main.activity_tasks.noWorkIv
import kotlinx.android.synthetic.main.activity_tasks.tasksRv
import kotlinx.android.synthetic.main.activity_tasks.welcome_tv
import org.koin.android.ext.android.inject

class TasksActivity : AppCompatActivity(), BottomSheetListener {

  private lateinit var adapter: ToDoListAdapter

  private val viewModel: TasksViewModel by inject()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(layout.activity_tasks)

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
        intent.putExtra(IntentConstants.TITLE, todo.title)
        intent.putExtra(IntentConstants.DESCRIPTION, todo.description)
        intent.putExtra(IntentConstants.ID, todo.id)
        intent.putExtra(IntentConstants.IMAGE_PATH, todo.imagePath)
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
