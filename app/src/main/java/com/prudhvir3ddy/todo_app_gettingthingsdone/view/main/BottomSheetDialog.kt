package com.prudhvir3ddy.todo_app_gettingthingsdone.view.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.prudhvir3ddy.todo_app_gettingthingsdone.R.layout
import kotlinx.android.synthetic.main.dialog.task_desc_et
import kotlinx.android.synthetic.main.dialog.task_name_et
import kotlinx.android.synthetic.main.dialog.view.save_tv

class BottomSheetDialog : BottomSheetDialogFragment() {

  private lateinit var mListener: BottomSheetListener

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {

    val v = inflater.inflate(layout.dialog, container, false)
    v.save_tv.setOnClickListener {
      val taskName = task_name_et.text.toString()
      val taskDesc = task_desc_et.text.toString()

      if (!taskName.isBlank()) {
        mListener.onSave(taskName, taskDesc)
        dismiss()
      }

    }
    return v
  }

  override fun onAttach(context: Context) {
    super.onAttach(context)
    mListener = context as BottomSheetListener
  }

  interface BottomSheetListener {
    fun onSave(taskName: String, taskDesc: String)
  }
}


