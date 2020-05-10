package com.prudhvir3ddy.todo_app_gettingthingsdone.view.main

import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.prudhvir3ddy.todo_app_gettingthingsdone.R.layout
import kotlinx.android.synthetic.main.dialog.save_btn
import kotlinx.android.synthetic.main.dialog.task_desc_et
import kotlinx.android.synthetic.main.dialog.task_name_et
import kotlinx.android.synthetic.main.dialog.view.save_btn
import kotlinx.android.synthetic.main.dialog.view.task_desc_et

class BottomSheetDialog : BottomSheetDialogFragment() {

  private lateinit var mListener: BottomSheetListener

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {

    val v = inflater.inflate(layout.dialog, container, false)
    setOnEditorAction(v)
    v.save_btn.setOnClickListener {
      val taskName = task_name_et.text.toString()
      val taskDesc = task_desc_et.text.toString()

      if (!taskName.isBlank()) {
        mListener.onSave(taskName, taskDesc)
        dismiss()
      }

    }
    return v
  }

  private fun setOnEditorAction(v: View) {
    v.task_desc_et.setOnEditorActionListener { v, actionId, event ->
      if (event != null && event.keyCode === KeyEvent.KEYCODE_ENTER || actionId == EditorInfo.IME_ACTION_DONE) {
        save_btn.performClick()
        true
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


