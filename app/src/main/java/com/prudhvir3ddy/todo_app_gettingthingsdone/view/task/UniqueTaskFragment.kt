package com.prudhvir3ddy.todo_app_gettingthingsdone.view.task

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.prudhvir3ddy.todo_app_gettingthingsdone.R.string
import com.prudhvir3ddy.todo_app_gettingthingsdone.storage.db.ToDo
import com.prudhvir3ddy.todo_app_gettingthingsdone.utils.hideKeyboard
import com.prudhvir3ddy.todo_app_gettingthingsdone.view.main.TasksViewModel
import com.prudhvir3ddy.todo_app_gettingthingsdone.view.task.UniqueTaskFragment.Companion.TaskType.ADD_TASK
import com.prudhvir3ddy.todo_app_gettingthingsdone.view.task.UniqueTaskFragment.Companion.TaskType.EDIT_TASK
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UniqueTaskFragment : Fragment() {

  private val args: UniqueTaskFragmentArgs by navArgs()
  private val viewModel: TasksViewModel by viewModels()

  companion object {
    enum class TaskType {
      ADD_TASK,
      EDIT_TASK
    }
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    return ComposeView(requireContext()).apply {
      setContent {
        UniqueTaskScreen(
          modifier = Modifier.padding(16.dp),
          onSaveButtonClicked = { onSaveButtonClicked(it) },
          args
        )
      }
    }
  }

  private fun onSaveButtonClicked(toDo: ToDo) {
    hideKeyboard(requireActivity())
    val taskName = toDo.title
    val taskDesc = toDo.description
    if (taskName.isNotBlank()) {
      actionAddTask(taskName, taskDesc)
    } else {
      Snackbar.make(
        requireView(),
        getString(string.task_name_cannot_be_empty),
        Snackbar.LENGTH_SHORT
      ).show()
    }
  }

  private fun actionAddTask(taskName: String, taskDesc: String) {
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
  }

}