package com.prudhvir3ddy.todo_app_gettingthingsdone.view.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.prudhvir3ddy.todo_app_gettingthingsdone.R
import com.prudhvir3ddy.todo_app_gettingthingsdone.databinding.ActivityLoginBinding
import com.prudhvir3ddy.todo_app_gettingthingsdone.view.main.TasksActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

  private lateinit var binding: ActivityLoginBinding
  private val viewModel: LoginViewModel by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setView()

    viewModel.isLoggedIn.observe(this, Observer {
      if (it) {
        val intent = Intent(this, TasksActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
      }
    })

    loadMainImage()
    addTextWatcher()

    binding.loginBtn.setOnClickListener {
      val fullName = binding.fullnameTil.editText?.text.toString()
      if (!fullName.isBlank())
        viewModel.getStarted(fullName)
      else
        binding.fullnameTil.error = getString(R.string.please_enter_your_name)
    }

  }

  private fun setView() {
    binding = ActivityLoginBinding.inflate(layoutInflater)
    setContentView(binding.root)
  }

  private fun loadMainImage() {
    Glide.with(this).load(R.drawable.master_plan).into(binding.masterPlanIv)
  }

  private fun addTextWatcher() {
    binding.fullnameTil.editText?.addTextChangedListener {
      binding.fullnameTil.error = ""
      if (it.isNullOrBlank())
        binding.fullnameTil.error = getString(R.string.please_enter_your_name)
    }
  }
}
