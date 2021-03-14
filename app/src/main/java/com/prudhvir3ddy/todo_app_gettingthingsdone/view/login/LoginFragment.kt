package com.prudhvir3ddy.todo_app_gettingthingsdone.view.login

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
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

  private val viewModel: LoginViewModel by viewModels()

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {

    return ComposeView(requireContext()).apply {
      setContent {
        LoginScreen(modifier = Modifier.padding(16.dp)) {
          if (it.isNotBlank()) {
            viewModel.getStarted(it)
          }
        }
      }
    }
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    viewModel.checkLogin()
    viewModel.isLoggedIn.observe(viewLifecycleOwner) {
      if (it.getContentIfNotHandled() == true) {
        val action = LoginFragmentDirections.actionLoginFragmentToTasksFragment()
        findNavController().navigate(action)
      }
    }
  }
}
