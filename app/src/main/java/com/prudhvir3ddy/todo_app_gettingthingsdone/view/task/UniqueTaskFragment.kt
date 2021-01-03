package com.prudhvir3ddy.todo_app_gettingthingsdone.view.task

import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.prudhvir3ddy.todo_app_gettingthingsdone.R
import com.prudhvir3ddy.todo_app_gettingthingsdone.R.string
import com.prudhvir3ddy.todo_app_gettingthingsdone.databinding.FragmentTaskUniqueBinding
import com.prudhvir3ddy.todo_app_gettingthingsdone.storage.db.ToDo
import com.prudhvir3ddy.todo_app_gettingthingsdone.view.main.TasksViewModel
import com.prudhvir3ddy.todo_app_gettingthingsdone.view.task.UniqueTaskFragment.Companion.TaskType.ADD_TASK
import com.prudhvir3ddy.todo_app_gettingthingsdone.view.task.UniqueTaskFragment.Companion.TaskType.EDIT_TASK
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UniqueTaskFragment : Fragment(R.layout.fragment_task_unique) {

  private val args: UniqueTaskFragmentArgs by navArgs()
  private val viewModel: TasksViewModel by viewModels()

  private var _binding: FragmentTaskUniqueBinding? = null
  private val binding get() = _binding!!

  companion object {
    enum class TaskType {
      ADD_TASK,
      EDIT_TASK
    }
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    _binding = FragmentTaskUniqueBinding.bind(view)
    setOnEditorAction()

    updateTitleOnTaskType(args.taskType)

    prePopulateTodo(args.todo)

    binding.saveBtn.setOnClickListener { onSaveButtonClicked() }

  }

  private fun onSaveButtonClicked() {
    val taskName = binding.taskNameEt.text.toString()
    val taskDesc = binding.taskDescEt.text.toString()
    if (!taskName.isBlank()) {
      if (args.taskType == ADD_TASK) {

        viewModel.onTaskSave(taskName, taskDesc)
      } else if (args.taskType == EDIT_TASK) {
        viewModel.onTaskUpdate(
          args.todo
            .copy(title = taskName, description = taskDesc)
        )
      }
      val action = UniqueTaskFragmentDirections.actionUniqueTaskFragmentToTasksFragment()
      findNavController().navigate(action)
    } else {
      Snackbar.make(
        requireView(),
        getString(string.task_name_cannot_be_empty),
        Snackbar.LENGTH_SHORT
      ).show()
    }
  }

  private fun prePopulateTodo(todo: ToDo) {
    binding.taskDescEt.setText(todo.description)
    binding.taskNameEt.setText(todo.title)
  }

  private fun updateTitleOnTaskType(taskType: TaskType) {
    when (taskType) {
      ADD_TASK -> binding.taskActionTv.text = getString(string.add_task)
      EDIT_TASK ->
        binding.taskActionTv.text = getString(string.edit_task)
    }
  }

  private fun setOnEditorAction() {
    binding.taskDescEt.setOnEditorActionListener { _, actionId, event ->
      if (event != null && event.keyCode == KeyEvent.KEYCODE_ENTER || actionId == EditorInfo.IME_ACTION_DONE) {
        binding.saveBtn.performClick()
      }
      false
    }
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }
}