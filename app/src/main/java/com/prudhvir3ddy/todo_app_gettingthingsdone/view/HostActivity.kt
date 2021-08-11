package com.prudhvir3ddy.todo_app_gettingthingsdone.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.prudhvir3ddy.todo_app_gettingthingsdone.view.login.LoginScreen
import com.prudhvir3ddy.todo_app_gettingthingsdone.view.login.LoginViewModel
import com.prudhvir3ddy.todo_app_gettingthingsdone.view.main.TasksScreen
import com.prudhvir3ddy.todo_app_gettingthingsdone.view.task.TaskType
import com.prudhvir3ddy.todo_app_gettingthingsdone.view.task.UniqueTaskScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HostActivity : ComponentActivity() {
  @ExperimentalMaterialApi
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      MaterialTheme {
        val navController = rememberNavController()
        val viewModel: LoginViewModel = hiltViewModel()
        NavHost(
          navController = navController,
          startDestination = if (viewModel.checkLogin()) Screens.TASKS else Screens.LOGIN
        ) {
          composable(Screens.LOGIN) {
            LoginScreen(navController = navController)
          }
          composable(Screens.TASKS) {
            TasksScreen(navController = navController)
          }
          composable(
            "${Screens.UNIQUE_TASK}?${Params.TASK_ID}={${Params.TASK_ID}}&${Params.TASK_TYPE}={${Params.TASK_TYPE}}",
            arguments = listOf(
              navArgument(Params.TASK_TYPE) {
                defaultValue = TaskType.ADD_TASK
                type = NavType.EnumType(TaskType::class.java)
              }, navArgument(Params.TASK_ID) {
                nullable = true
                defaultValue = null
                type = NavType.StringType
              })
          ) {
            val taskId = remember {
              it.arguments?.getString(Params.TASK_ID)
            }
            val type: TaskType = remember {
              it.arguments?.get(Params.TASK_TYPE) as TaskType
            }
            UniqueTaskScreen(navController = navController, taskId = taskId, taskType = type)
          }
        }
      }
    }
  }

}

object Screens {
  const val LOGIN = "login_screen"
  const val TASKS = "tasks_screen"
  const val UNIQUE_TASK = "unique_task_screen"
}

object Params {
  const val TASK_ID = "taskId"
  const val TASK_TYPE = "taskType"
}