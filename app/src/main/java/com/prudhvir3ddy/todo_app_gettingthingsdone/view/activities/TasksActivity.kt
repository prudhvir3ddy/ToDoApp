package com.prudhvir3ddy.todo_app_gettingthingsdone.view.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.prudhvir3ddy.todo_app_gettingthingsdone.R.layout
import com.prudhvir3ddy.todo_app_gettingthingsdone.R.string
import com.prudhvir3ddy.todo_app_gettingthingsdone.model.ToDo
import com.prudhvir3ddy.todo_app_gettingthingsdone.storage.SharedPrefs
import com.prudhvir3ddy.todo_app_gettingthingsdone.view.BottomSheetDialog
import com.prudhvir3ddy.todo_app_gettingthingsdone.view.BottomSheetDialog.BottomSheetListener
import com.prudhvir3ddy.todo_app_gettingthingsdone.view.ItemClickListener
import com.prudhvir3ddy.todo_app_gettingthingsdone.view.ToDoListAdapter
import kotlinx.android.synthetic.main.activity_tasks.add_task_fab
import kotlinx.android.synthetic.main.activity_tasks.tasks_rv
import kotlinx.android.synthetic.main.activity_tasks.welcome_tv

class TasksActivity : AppCompatActivity(), BottomSheetListener {

  lateinit var sharedPrefs: SharedPrefs
  private val tasksList = arrayListOf<ToDo>()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(layout.activity_tasks)

    sharedPrefs =
      SharedPrefs(this)
    setTitle()

    add_task_fab.setOnClickListener {
      setUpBottomDialog()
    }

    setUpRecyclerView()

  }

  private fun setUpRecyclerView() {
    val click = object :
      ItemClickListener {
      override fun onClick() {
      }
    }

    val adapter =
      ToDoListAdapter(click)
    tasks_rv.adapter = adapter
    adapter.submitList(tasksList)
    tasks_rv.addItemDecoration(DividerItemDecoration(tasks_rv.context, VERTICAL))
  }

  private fun setUpBottomDialog() {
    val bottomSheetDialog =
      BottomSheetDialog()
    bottomSheetDialog.show(supportFragmentManager, "ADD_TASK")
  }

  private fun setTitle() {
    welcome_tv.text = String.format(getString(string.welcome), sharedPrefs.getFullName())
  }

  override fun onSave(taskName: String, taskDesc: String) {
    tasksList.add(
      ToDo(
        taskName,
        taskDesc
      )
    )
  }
}
