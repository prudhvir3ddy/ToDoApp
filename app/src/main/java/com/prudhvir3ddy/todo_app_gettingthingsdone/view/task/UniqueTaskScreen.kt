package com.prudhvir3ddy.todo_app_gettingthingsdone.view.task

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.prudhvir3ddy.todo_app_gettingthingsdone.R.string
import com.prudhvir3ddy.todo_app_gettingthingsdone.storage.db.ToDo
import com.prudhvir3ddy.todo_app_gettingthingsdone.view.Screens
import com.prudhvir3ddy.todo_app_gettingthingsdone.view.main.TasksViewModel
import com.prudhvir3ddy.todo_app_gettingthingsdone.view.task.TaskType.ADD_TASK
import com.prudhvir3ddy.todo_app_gettingthingsdone.view.task.TaskType.EDIT_TASK

@Composable
fun UniqueTaskScreen(
  modifier: Modifier = Modifier,
  navController: NavController,
  viewModel: TasksViewModel = hiltViewModel(),
  taskId: String?,
  taskType: TaskType = ADD_TASK
) {

  val onSaveButtonClicked = { savedTask: ToDo ->
    when (taskType) {
      ADD_TASK -> viewModel.onTaskSave(savedTask.title, savedTask.description)
      EDIT_TASK -> viewModel.onTaskUpdate(savedTask)
    }

    navController.navigate(Screens.TASKS) {
      popUpTo(Screens.TASKS) {
        inclusive = true
      }
    }
  }

  Column(
    modifier = modifier
      .fillMaxHeight()
      .fillMaxWidth()
      .padding(16.dp)
  ) {

    val task = remember {
      if (taskId != null) {
        viewModel.getTaskByIdAsync(taskId)
      } else {
        ToDo()
      }
    }

    val (titleText, setTitleText) = remember { mutableStateOf(task.title) }
    val (descriptionText, setDescriptionText) = remember { mutableStateOf(task.description) }
    Row(
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically,
      modifier = Modifier.fillMaxWidth()
    ) {
      TaskModeText(taskType = taskType)
      SaveTaskButton(
        onSaveButtonClicked = {
          task.title = titleText
          task.description = descriptionText
          onSaveButtonClicked(
            task
          )
        }
      )
    }
    TitleTextField(
      text = titleText,
      setText = setTitleText,
      modifier = Modifier
        .fillMaxWidth()
        .padding(top = 16.dp)
    )
    DescriptionTextField(
      text = descriptionText,
      setText = setDescriptionText,
      modifier = Modifier.fillMaxWidth()
    )
  }
}

@Composable
fun SaveTaskButton(modifier: Modifier = Modifier, onSaveButtonClicked: () -> Unit) {
  TextButton(onClick = { onSaveButtonClicked() }) {
    Text(text = stringResource(id = string.save), modifier = modifier)
  }
}

@Composable
fun TaskModeText(
  modifier: Modifier = Modifier,
  taskType: TaskType = ADD_TASK
) {
  when (taskType) {
    ADD_TASK -> Text(
      modifier = modifier,
      text = stringResource(id = string.add_task),
      style = MaterialTheme.typography.h5
    )
    EDIT_TASK -> Text(
      modifier = modifier,
      text = stringResource(id = string.edit_task),
      style = MaterialTheme.typography.h5
    )
  }

}

@Composable
fun TitleTextField(
  modifier: Modifier = Modifier,
  text: String,
  setText: (String) -> Unit
) {
  TextField(
    value = text,
    onValueChange = setText,
    modifier = modifier,
    colors = taskTextFieldColors(),
    label = { Text(text = stringResource(id = string.task_name)) }
  )
}

@Composable
fun taskTextFieldColors(): TextFieldColors = TextFieldDefaults.textFieldColors(
  focusedIndicatorColor = Color.Transparent,
  disabledIndicatorColor = Color.Transparent,
  unfocusedIndicatorColor = Color.Transparent,
  backgroundColor = Color.Transparent,
)

@Composable
fun DescriptionTextField(
  modifier: Modifier = Modifier,
  text: String,
  setText: (String) -> Unit
) {
  TextField(
    value = text,
    onValueChange = setText,
    modifier = modifier,
    colors = taskTextFieldColors(),
    label = { Text(text = stringResource(id = string.task_description)) }
  )
}