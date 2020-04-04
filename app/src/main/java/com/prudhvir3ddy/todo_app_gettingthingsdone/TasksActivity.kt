package com.prudhvir3ddy.todo_app_gettingthingsdone

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.prudhvir3ddy.todo_app_gettingthingsdone.BottomSheetDialog.BottomSheetListener
import kotlinx.android.synthetic.main.activity_tasks.add_task_fab
import kotlinx.android.synthetic.main.activity_tasks.welcome_tv

class TasksActivity : AppCompatActivity(), BottomSheetListener {

  lateinit var sharedPrefs: SharedPrefs

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_tasks)

    sharedPrefs = SharedPrefs(this)
    setTitle()

    add_task_fab.setOnClickListener {
      setUpBottomDialog()
    }

  }

  private fun setUpBottomDialog() {
    val bottomSheetDialog = BottomSheetDialog()
    bottomSheetDialog.show(supportFragmentManager, "ADD_TASK")
  }

  private fun setTitle() {
    welcome_tv.text = String.format(getString(R.string.welcome), sharedPrefs.getFullName())
  }

  override fun onSave(taskName: String, taskDesc: String) {
    Toast.makeText(this, "$taskName $taskDesc", Toast.LENGTH_SHORT).show()
  }
}
