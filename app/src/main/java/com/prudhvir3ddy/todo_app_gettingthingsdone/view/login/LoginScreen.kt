package com.prudhvir3ddy.todo_app_gettingthingsdone.view.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign.Start
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.prudhvir3ddy.todo_app_gettingthingsdone.R.mipmap
import com.prudhvir3ddy.todo_app_gettingthingsdone.R.string

@Composable
fun LoginScreen(
  modifier: Modifier = Modifier,
  onLoginButtonClicked: (String) -> Unit
) {
  Column(
    modifier = modifier
      .fillMaxWidth()
      .fillMaxHeight()
  ) {
    GreetingText()
    PlanText()
    Box(
      modifier = Modifier
        .fillMaxWidth()
        .height(200.dp),
      contentAlignment = Alignment.Center
    ) {
      Image(
        painter = painterResource(id = mipmap.ic_launcher_round),
        contentDescription = null, modifier = modifier.size(100.dp),
        alignment = Alignment.Center
      )
    }
    val (text, setText) = remember { mutableStateOf("") }
    LoginTextField(text = text, setText = setText)
    LoginButton(onClick = onLoginButtonClicked, text = text)
  }
}

@Composable
fun GreetingText(
  modifier: Modifier = Modifier,
  text: String = stringResource(id = string.hi_it_s_great_to_see_you_here)
) {

  Text(
    text = text,
    textAlign = Start,
    style = MaterialTheme.typography.h5,
    modifier = modifier
      .fillMaxWidth()
      .padding(top = 16.dp)
  )
}

@Composable
fun PlanText(
  modifier: Modifier = Modifier,
  text: String = stringResource(id = string.let_s_get_started_with_a_plan)
) {

  Text(
    text = text, textAlign = Start, style = MaterialTheme.typography.h6, modifier = modifier
      .fillMaxWidth()
      .padding(top = 16.dp)
  )
}

@Composable
fun LoginTextField(
  modifier: Modifier = Modifier,
  text: String,
  setText: (String) -> Unit,
  hint: String = stringResource(id = string.please_enter_your_name),
) {
  OutlinedTextField(
    value = text,
    onValueChange = setText,
    singleLine = true,
    label = { Text(text = hint) },
    modifier = modifier
      .fillMaxWidth()
      .padding(top = 8.dp)
  )
}

@Composable
fun LoginButton(
  modifier: Modifier = Modifier,
  onClick: (String) -> Unit,
  text: String
) {
  Button(
    onClick = { onClick(text) },
    enabled = text.isNotBlank(),
    modifier = modifier
      .fillMaxWidth()
      .padding(top = 16.dp),
  ) {
    Text(text = stringResource(id = string.log_in))
  }
}

@Preview
@Composable
fun PreviewLoginScreen() {
  LoginScreen {

  }
}