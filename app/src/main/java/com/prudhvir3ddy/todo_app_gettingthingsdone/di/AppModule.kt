package com.prudhvir3ddy.todo_app_gettingthingsdone.di

import android.content.Context
import com.prudhvir3ddy.todo_app_gettingthingsdone.storage.SharedPrefs
import com.prudhvir3ddy.todo_app_gettingthingsdone.storage.db.ToDoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
class AppModule {

  @Singleton
  @Provides
  fun provideSharedPrefs(@ApplicationContext context: Context): SharedPrefs {
    return SharedPrefs(context)
  }

  @Singleton
  @Provides
  fun provideToDoDatabase(@ApplicationContext context: Context): ToDoDatabase {
    return ToDoDatabase.getInstance(context)
  }
}