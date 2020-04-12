package com.prudhvir3ddy.todo_app_gettingthingsdone.view.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import androidx.work.ExistingPeriodicWorkPolicy.KEEP
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.prudhvir3ddy.todo_app_gettingthingsdone.R.layout
import com.prudhvir3ddy.todo_app_gettingthingsdone.R.string
import com.prudhvir3ddy.todo_app_gettingthingsdone.storage.SharedPrefs
import com.prudhvir3ddy.todo_app_gettingthingsdone.storage.db.ToDo
import com.prudhvir3ddy.todo_app_gettingthingsdone.storage.db.ToDoDatabase
import com.prudhvir3ddy.todo_app_gettingthingsdone.utils.IntentConstants
import com.prudhvir3ddy.todo_app_gettingthingsdone.view.BottomSheetDialog
import com.prudhvir3ddy.todo_app_gettingthingsdone.view.BottomSheetDialog.BottomSheetListener
import com.prudhvir3ddy.todo_app_gettingthingsdone.view.ItemClickListener
import com.prudhvir3ddy.todo_app_gettingthingsdone.view.ToDoListAdapter
import com.prudhvir3ddy.todo_app_gettingthingsdone.workmanager.MyWorker
import kotlinx.android.synthetic.main.activity_tasks.add_task_fab
import kotlinx.android.synthetic.main.activity_tasks.tasks_rv
import kotlinx.android.synthetic.main.activity_tasks.welcome_tv
import org.koin.android.ext.android.inject
import java.util.concurrent.TimeUnit.MINUTES

class TasksActivity : AppCompatActivity(), BottomSheetListener {

  private lateinit var sharedPrefs: SharedPrefs
  private val tasksList = arrayListOf<ToDo>()
  private lateinit var adapter: ToDoListAdapter

  private val toDoDatabase: ToDoDatabase by inject()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(layout.activity_tasks)

    sharedPrefs =
      SharedPrefs(this)
    setTitle()

    add_task_fab.setOnClickListener {
      setUpBottomDialog()
    }

    getDataFromDb()
    setUpRecyclerView()
    setUpWorkManager()

  }

  private fun setUpWorkManager() {
    val request = PeriodicWorkRequest.Builder(MyWorker::class.java, 15, MINUTES)
      .build()
    WorkManager.getInstance(this).enqueueUniquePeriodicWork("boo", KEEP, request)
  }

  private fun getDataFromDb() {
    toDoDatabase.databaseWriteExecutor.execute {
      tasksList.addAll(toDoDatabase.todoDao().getAll())
    }
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
        toDoDatabase.databaseWriteExecutor.execute {
          toDoDatabase.todoDao().updateToDo(todo)
        }
      }
    }

    adapter =
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

    toDoDatabase.databaseWriteExecutor.execute {
      toDoDatabase.todoDao().insertToDo(
        ToDo(
          title = taskName,
          description = taskDesc
        )
      )
    }

    tasksList.add(
      ToDo(
        title = taskName,
        description = taskDesc
      )
    )

    adapter.notifyDataSetChanged()

  }
}
