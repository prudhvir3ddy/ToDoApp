package com.prudhvir3ddy.todo_app_gettingthingsdone.view.main

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Checkbox
import androidx.compose.material.DismissDirection.EndToStart
import androidx.compose.material.DismissDirection.StartToEnd
import androidx.compose.material.DismissValue.Default
import androidx.compose.material.DismissValue.DismissedToEnd
import androidx.compose.material.DismissValue.DismissedToStart
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.prudhvir3ddy.todo_app_gettingthingsdone.R
import com.prudhvir3ddy.todo_app_gettingthingsdone.R.string
import com.prudhvir3ddy.todo_app_gettingthingsdone.storage.db.ToDo
import com.prudhvir3ddy.todo_app_gettingthingsdone.view.Params
import com.prudhvir3ddy.todo_app_gettingthingsdone.view.Screens
import com.prudhvir3ddy.todo_app_gettingthingsdone.view.task.TaskType

@Composable
fun WelcomeText(userName: String) {
  Text(
    text = String.format(stringResource(id = string.welcome), userName),
    style = MaterialTheme.typography.h5
  )
}

@ExperimentalMaterialApi
@Composable
fun TasksScreen(
  modifier: Modifier = Modifier,
  navController: NavController,
  viewModel: TasksViewModel = hiltViewModel()
) {

  val tasks: List<ToDo> by viewModel.tasksList.observeAsState(initial = emptyList())

  val onTaskAddButtonClicked = {
    navController.navigate(Screens.UNIQUE_TASK) {
      popUpTo(Screens.TASKS)
    }
  }

  Scaffold(floatingActionButton = {
    AddTaskFabButton(onTaskAddButtonClicked)
  }) {

    val onTaskClicked = { task: ToDo ->
      navController.navigate("${Screens.UNIQUE_TASK}?${Params.TASK_ID}=${task.id}&${Params.TASK_TYPE}=${TaskType.EDIT_TASK}") {
        popUpTo(Screens.TASKS)
      }
    }

    val onTaskToggle = { task: ToDo, toggle: Boolean ->
      viewModel.onTaskToggle(task, toggle)
    }

    LazyColumn(modifier = modifier.padding(16.dp)) {
      item {
        WelcomeText(viewModel.getFirstName())
      }
      if (tasks.isNotEmpty()) {
        items(tasks) { task ->
          TasksSwipeable(task, viewModel, onTaskClicked, onTaskToggle)
          Divider(color = Color.LightGray)
        }
      } else {
        item {
          ShowNoTasks()
        }
      }
    }
  }
}

@Composable
fun AddTaskFabButton(onTaskAddButtonClicked: () -> Unit) {
  FloatingActionButton(
    onClick = { onTaskAddButtonClicked() },
    content = {
      Icon(
        imageVector = Icons.Filled.Add,
        contentDescription = stringResource(id = string.add_task)
      )
    }
  )
}

@ExperimentalMaterialApi
@Composable
fun ToDoCard(
  task: ToDo,
  onTaskClicked: (ToDo) -> Unit,
  onTaskToggle: (ToDo, Boolean) -> Unit,
  modifier: Modifier = Modifier,
) {

  Row(
    modifier = modifier
      .fillMaxWidth()
      .clickable {
        onTaskClicked(task)
      }
      .padding(16.dp), verticalAlignment = Alignment.CenterVertically
  ) {
    Checkbox(
      checked = task.isCompleted,
      onCheckedChange = {
        onTaskToggle(task, it)
      }
    )

    Column(
      modifier = Modifier
        .fillMaxSize()
        .padding(start = 16.dp),
    ) {
      Text(text = task.title, style = MaterialTheme.typography.body1)
      Text(text = task.description, style = MaterialTheme.typography.body2)
    }
  }
}

@ExperimentalMaterialApi
@Composable
fun TasksSwipeable(
  task: ToDo,
  viewModel: TasksViewModel,
  onTaskClicked: (ToDo) -> Unit,
  onTaskToggle: (ToDo, Boolean) -> Unit,
  modifier: Modifier = Modifier
) {
  val dismissState = rememberDismissState(
    confirmStateChange = {
      if (it == DismissedToEnd)
        viewModel.onTaskDelete(task.id)
      it != DismissedToEnd
    }
  )
  SwipeToDismiss(directions = setOf(StartToEnd), state = dismissState, background = {
    val direction = dismissState.dismissDirection ?: return@SwipeToDismiss
    val color by animateColorAsState(
      when (dismissState.targetValue) {
        Default -> Color.LightGray
        DismissedToEnd -> Color.Red
        DismissedToStart -> return@SwipeToDismiss
      }
    )
    val alignment = when (direction) {
      StartToEnd -> Alignment.CenterStart
      EndToStart -> return@SwipeToDismiss
    }
    val icon = Icons.Default.Delete
    val scale by animateFloatAsState(
      if (dismissState.targetValue == Default) 0.75f else 1f
    )

    Box(
      modifier
        .fillMaxSize()
        .background(color)
        .padding(horizontal = 20.dp),
      contentAlignment = alignment
    ) {
      Icon(
        icon,
        contentDescription = stringResource(id = string.delete_task),
        modifier = Modifier.scale(scale)
      )
    }
  }, dismissThresholds = { direction ->
    FractionalThreshold(0.25f)
  }) {
    ToDoCard(task, onTaskClicked, onTaskToggle)
  }
}

@Composable
fun ShowNoTasks() {
  Image(
    alignment = Alignment.Center,
    modifier = Modifier
      .fillMaxHeight()
      .padding(32.dp),
    painter = painterResource(R.drawable.add_task),
    contentDescription = stringResource(id = string.no_tasks)
  )
}

//@ExperimentalMaterialApi
//@Preview(showBackground = true, backgroundColor = 0xffffff)
//@Composable
//fun PreviewTasksScreen() {
//  TasksScreen(
//    "",
//    tasks = emptyList(),
//    onTaskStatusChanged = { todo, b ->
//
//    },
//    onTaskAddButtonClicked = {
//
//    }, onTaskSwiped = {
//
//    }, onTaskClicked = {
//
//    })
//}