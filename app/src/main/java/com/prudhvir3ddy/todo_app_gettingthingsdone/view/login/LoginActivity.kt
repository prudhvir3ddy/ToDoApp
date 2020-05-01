package com.prudhvir3ddy.todo_app_gettingthingsdone.view.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.bumptech.glide.Glide
import com.prudhvir3ddy.todo_app_gettingthingsdone.R
import com.prudhvir3ddy.todo_app_gettingthingsdone.R.layout
import com.prudhvir3ddy.todo_app_gettingthingsdone.ToDoApp
import com.prudhvir3ddy.todo_app_gettingthingsdone.storage.SharedPrefs
import com.prudhvir3ddy.todo_app_gettingthingsdone.view.main.TasksActivity
import kotlinx.android.synthetic.main.activity_login.fullname_til
import kotlinx.android.synthetic.main.activity_login.login_btn
import kotlinx.android.synthetic.main.activity_login.master_plan_iv
import javax.inject.Inject

class LoginActivity : AppCompatActivity() {

  @Inject
  lateinit var sharedPrefs: SharedPrefs

  override fun onCreate(savedInstanceState: Bundle?) {
    (application as ToDoApp).appComponent.inject(this)
    super.onCreate(savedInstanceState)
    setContentView(layout.activity_login)
    Glide.with(this).load(R.drawable.master_plan).into(master_plan_iv)
    addTextWatcher()

    login_btn.setOnClickListener {
      val fullname = fullname_til.editText?.text.toString()
      getStarted(fullname)
    }
  }

  private fun getStarted(fullname: String) {
    if (!fullname.isBlank()) {
      sharedPrefs.setLogin(true)
      sharedPrefs.setFullName(fullname)
      val intent = Intent(this, TasksActivity::class.java)
      intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
      startActivity(intent)
    } else {
      fullname_til.error = getString(R.string.please_enter_your_name)
    }
  }

  private fun addTextWatcher() {
    fullname_til.editText?.addTextChangedListener {
      fullname_til.error = ""
      if (it.isNullOrBlank())
        fullname_til.error = getString(R.string.please_enter_your_name)
    }
  }
}
