package com.prudhvir3ddy.todo_app_gettingthingsdone.view.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.prudhvir3ddy.todo_app_gettingthingsdone.R
import com.prudhvir3ddy.todo_app_gettingthingsdone.ToDoApp
import com.prudhvir3ddy.todo_app_gettingthingsdone.databinding.ActivityLoginBinding
import com.prudhvir3ddy.todo_app_gettingthingsdone.view.main.TasksActivity
import com.prudhvir3ddy.todo_app_gettingthingsdone.viewmodels.ViewModelFactory
import javax.inject.Inject

class LoginActivity : AppCompatActivity() {

  private lateinit var binding: ActivityLoginBinding
  private lateinit var viewModel: LoginViewModel

  @Inject
  lateinit var viewModelFactory: ViewModelFactory

  override fun onCreate(savedInstanceState: Bundle?) {
    (application as ToDoApp).appComponent.inject(this)
    super.onCreate(savedInstanceState)
    setView()

    setUpViewModel()

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

  private fun setUpViewModel() {
    viewModel = ViewModelProvider(this, viewModelFactory)[LoginViewModel::class.java]
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
