package com.prudhvir3ddy.todo_app_gettingthingsdone.di

import android.content.Context
import com.prudhvir3ddy.todo_app_gettingthingsdone.storage.SharedPrefs
import com.prudhvir3ddy.todo_app_gettingthingsdone.storage.db.ToDoDatabase
import com.prudhvir3ddy.todo_app_gettingthingsdone.viewmodels.TasksViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
  single {
    provideSharedPrefs(get())
  }

  single {
    provideDatabase(get())
  }
}

fun provideDatabase(context: Context): ToDoDatabase {
  return ToDoDatabase.getInstance(context)
}

fun provideSharedPrefs(context: Context): SharedPrefs {

  return SharedPrefs(context)

}

val viewModelModule = module {
  viewModel { TasksViewModel(get()) }
}