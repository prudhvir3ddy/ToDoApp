package com.prudhvir3ddy.todo_app_gettingthingsdone.view.main

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.prudhvir3ddy.todo_app_gettingthingsdone.R.string
import com.prudhvir3ddy.todo_app_gettingthingsdone.databinding.TaskInputDialogBinding
import com.prudhvir3ddy.todo_app_gettingthingsdone.storage.db.ToDo

class BottomSheetDialog(
  private val viewModel: TasksViewModel,
  private val toDo: ToDo
) : BottomSheetDialogFragment() {

  private lateinit var binding: TaskInputDialogBinding

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {

    binding = TaskInputDialogBinding.inflate(inflater, container, false)
    setOnEditorAction()

    when (tag) {
      BottomSheetTag.ADD_TASK -> binding.taskActionTv.text = getString(string.add_task)
      BottomSheetTag.EDIT_TASK ->
        binding.taskActionTv.text = getString(string.edit_task)
    }

    binding.taskDescEt.setText(toDo.description)
    binding.taskNameEt.setText(toDo.title)
    binding.saveBtn.setOnClickListener {
      val taskName = binding.taskNameEt.text.toString()
      val taskDesc = binding.taskDescEt.text.toString()

      if (tag == BottomSheetTag.ADD_TASK) {
        if (!taskName.isBlank()) {
          viewModel.onTaskSave(taskName, taskDesc)
          dismiss()
        }
      } else if (tag == BottomSheetTag.EDIT_TASK) {
        if (!taskName.isBlank()) {
          viewModel.onTaskUpdate(
            toDo
              .copy(title = taskName, description = taskDesc)
          )
          dismiss()
        }
      }
    }
    return binding.root
  }

  private fun setOnEditorAction() {
    binding.taskDescEt.setOnEditorActionListener { _, actionId, event ->
      if (event != null && event.keyCode == KeyEvent.KEYCODE_ENTER || actionId == EditorInfo.IME_ACTION_DONE) {
        binding.saveBtn.performClick()
      }
      false
    }
  }

}

object BottomSheetTag {
  const val ADD_TASK = "addTask"
  const val EDIT_TASK = "editTask"
}


