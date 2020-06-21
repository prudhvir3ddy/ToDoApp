package com.prudhvir3ddy.todo_app_gettingthingsdone.view.main

import android.content.Intent
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
import com.prudhvir3ddy.todo_app_gettingthingsdone.storage.db.ToDo
import com.prudhvir3ddy.todo_app_gettingthingsdone.utils.IntentConstants
import com.prudhvir3ddy.todo_app_gettingthingsdone.view.detail.DetailActivity
import com.prudhvir3ddy.todo_app_gettingthingsdone.view.main.BottomSheetDialog.BottomSheetListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_tasks.noWorkIv
import kotlinx.android.synthetic.main.activity_tasks.tasksRv

@AndroidEntryPoint
class TasksActivity : AppCompatActivity(), BottomSheetListener {

  companion object {
    private const
    val DETAIL_CODE: Int = 3
  }

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
    viewModel.getDataFromDb()
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
    val click = object :
      ItemClickListener {
      override fun onClick(todo: ToDo) {
        val intent = Intent(this@TasksActivity, DetailActivity::class.java)
        intent.putExtra(IntentConstants.TODO, todo)
        startActivityForResult(intent, DETAIL_CODE)
      }

      override fun onUpdate(todo: ToDo) {
        viewModel.onTaskUpdate(todo)
      }
    }

    adapter =
      ToDoListAdapter(listOf(), click)
    binding.tasksRv.adapter = adapter
    binding.tasksRv.addItemDecoration(DividerItemDecoration(tasksRv.context, VERTICAL))
  }

  private fun setUpBottomDialog() {
    val bottomSheetDialog =
      BottomSheetDialog()
    bottomSheetDialog.show(supportFragmentManager, "ADD_TASK")
  }

  private fun setTitle() {
    binding.welcomeTv.text = String.format(getString(string.welcome), viewModel.getFirstName())
  }

  override fun onSave(taskName: String, taskDesc: String) {
    viewModel.onTaskSave(taskName, taskDesc)
    adapter.notifyItemInserted(adapter.itemCount)

  }

  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)
    viewModel.onTaskUpdate(data!!.getParcelableExtra(IntentConstants.TODO)!!)
  }
}
