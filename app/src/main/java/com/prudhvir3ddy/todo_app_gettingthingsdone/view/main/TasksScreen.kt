package com.prudhvir3ddy.todo_app_gettingthingsdone.view.main

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.prudhvir3ddy.todo_app_gettingthingsdone.R
import com.prudhvir3ddy.todo_app_gettingthingsdone.R.string
import com.prudhvir3ddy.todo_app_gettingthingsdone.storage.db.ToDo

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
  userName: String,
  tasks: List<ToDo>,
  modifier: Modifier = Modifier,
  onTaskStatusChanged: (ToDo, Boolean) -> Unit,
  onTaskAddButtonClicked: () -> Unit
) {
  Scaffold(floatingActionButton = {
    FloatingActionButton(
      onClick = onTaskAddButtonClicked,
      content = {
        Icon(
          imageVector = Icons.Filled.Add,
          contentDescription = stringResource(id = string.add_task)
        )
      }
    )
  }) {
    LazyColumn(modifier = modifier.padding(16.dp)) {
      item {
        WelcomeText(userName)
      }
      if (tasks.isNotEmpty()) {
        items(tasks) { task ->
          val dismissState = rememberDismissState(
            confirmStateChange = {
              it != DismissedToEnd
            }
          )
          SwipeToDismiss(state = dismissState, background = {
            val direction = dismissState.dismissDirection ?: return@SwipeToDismiss
            val color by animateColorAsState(
              when (dismissState.targetValue) {
                Default -> Color.LightGray
                DismissedToEnd -> Color.Green
                DismissedToStart -> Color.Red
              }
            )
            val alignment = when (direction) {
              StartToEnd -> Alignment.CenterStart
              EndToStart -> Alignment.CenterEnd
            }
            val icon = when (direction) {
              StartToEnd -> Icons.Default.Done
              EndToStart -> Icons.Default.Delete
            }
            val scale by animateFloatAsState(
              if (dismissState.targetValue == Default) 0.75f else 1f
            )

            Box(
              Modifier
                .fillMaxSize()
                .background(color)
                .padding(horizontal = 20.dp),
              contentAlignment = alignment
            ) {
              Icon(
                icon,
                contentDescription = "Localized description",
                modifier = Modifier.scale(scale)
              )
            }
          }, dismissThresholds = { direction ->
            FractionalThreshold(0.25f)
          }) {
            ToDoCard(task, onTaskStatusChanged, modifier.padding(16.dp))
          }
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
fun ToDoCard(
  task: ToDo,
  onTaskStatusChanged: (ToDo, Boolean) -> Unit,
  modifier: Modifier = Modifier
) {
  Row(modifier = modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
    Checkbox(
      checked = task.isCompleted,
      onCheckedChange = {
        onTaskStatusChanged(task, it)
      }
    )

    Column(
      modifier = Modifier
        .fillMaxSize(),
    ) {
      Text(text = task.title, style = MaterialTheme.typography.body1)
      Text(text = task.description, style = MaterialTheme.typography.body2)
    }
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

@ExperimentalMaterialApi
@Preview(showBackground = true, backgroundColor = 0xffffff)
@Composable
fun PreviewTasksScreen() {
  TasksScreen(
    "",
    tasks = emptyList(),
    onTaskStatusChanged = { todo, b ->

    },
    onTaskAddButtonClicked = {

    })
}