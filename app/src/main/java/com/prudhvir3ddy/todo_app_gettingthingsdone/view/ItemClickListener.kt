package com.prudhvir3ddy.todo_app_gettingthingsdone.view

import com.prudhvir3ddy.todo_app_gettingthingsdone.storage.db.ToDo

interface ItemClickListener {
  fun onClick(todo: ToDo)
  fun onUpdate(todo: ToDo)
}