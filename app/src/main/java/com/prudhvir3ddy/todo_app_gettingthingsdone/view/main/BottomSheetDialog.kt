package com.prudhvir3ddy.todo_app_gettingthingsdone.view.main

import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.prudhvir3ddy.todo_app_gettingthingsdone.databinding.DialogBinding

class BottomSheetDialog : BottomSheetDialogFragment() {

  private lateinit var mListener: BottomSheetListener

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
        mListener.onSave(taskName, taskDesc)
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

  override fun onAttach(context: Context) {
    super.onAttach(context)
    mListener = context as BottomSheetListener
  }

  interface BottomSheetListener {
    fun onSave(taskName: String, taskDesc: String)
  }
}


