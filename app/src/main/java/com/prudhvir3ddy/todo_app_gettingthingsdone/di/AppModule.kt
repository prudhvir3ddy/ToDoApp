package com.prudhvir3ddy.todo_app_gettingthingsdone.di

import android.content.Context
import com.prudhvir3ddy.todo_app_gettingthingsdone.storage.SharedPrefs
import com.prudhvir3ddy.todo_app_gettingthingsdone.storage.db.ToDoDao
import com.prudhvir3ddy.todo_app_gettingthingsdone.storage.db.ToDoDatabase
import org.koin.dsl.module

val appModule = module {
  single {
    provideSharedPrefs(get())
  }

  single {
    provideDatabaseDao(get())
  }
}

fun provideDatabaseDao(context: Context): ToDoDao {
  return ToDoDatabase.getInstance(context).todoDao()
}

fun provideSharedPrefs(context: Context): SharedPrefs {

  return SharedPrefs(context)

}
