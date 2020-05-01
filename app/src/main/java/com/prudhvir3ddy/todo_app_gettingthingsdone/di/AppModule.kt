package com.prudhvir3ddy.todo_app_gettingthingsdone.di

import android.content.Context
import com.prudhvir3ddy.todo_app_gettingthingsdone.storage.SharedPrefs
import com.prudhvir3ddy.todo_app_gettingthingsdone.storage.db.ToDoDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

  @Singleton
  @Provides
  fun provideSharedPrefs(context: Context): SharedPrefs {
    return SharedPrefs(context)
  }

  @Singleton
  @Provides
  fun provideToDoDatabase(context: Context): ToDoDatabase {
    return ToDoDatabase.getInstance(context)
  }
}