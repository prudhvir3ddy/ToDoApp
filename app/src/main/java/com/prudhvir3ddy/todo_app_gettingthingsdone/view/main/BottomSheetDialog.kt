package com.prudhvir3ddy.todo_app_gettingthingsdone.view.main

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.prudhvir3ddy.todo_app_gettingthingsdone.databinding.DialogBinding

class BottomSheetDialog(
  private val viewModel: TasksViewModel
) : BottomSheetDialogFragment() {

  private lateinit var binding: DialogBinding

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {

    binding = DialogBinding.inflate(inflater, container, false)
    setOnEditorAction()
    binding.saveBtn.setOnClickListener {
      val taskName = binding.taskNameEt.text.toString()
      val taskDesc = binding.taskDescEt.text.toString()

      if (!taskName.isBlank()) {
        viewModel.onTaskSave(taskName, taskDesc)
        dismiss()
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


