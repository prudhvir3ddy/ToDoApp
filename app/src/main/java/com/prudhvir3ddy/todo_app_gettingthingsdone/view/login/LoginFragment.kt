package com.prudhvir3ddy.todo_app_gettingthingsdone.view.login

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.prudhvir3ddy.todo_app_gettingthingsdone.R
import com.prudhvir3ddy.todo_app_gettingthingsdone.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {

  private var _binding: FragmentLoginBinding? = null
  private val binding get() = _binding!!

  private val viewModel: LoginViewModel by viewModels()

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    _binding = FragmentLoginBinding.bind(view)

    viewModel.checkLogin()
    viewModel.isLoggedIn.observe(viewLifecycleOwner) {
      if (it.getContentIfNotHandled() == true) {
        val action = LoginFragmentDirections.actionLoginFragmentToTasksFragment()
        findNavController().navigate(action)
      }
    }

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

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }
}
