package com.prudhvir3ddy.todo_app_gettingthingsdone.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.prudhvir3ddy.todo_app_gettingthingsdone.viewmodels.TasksViewModel
import com.prudhvir3ddy.todo_app_gettingthingsdone.viewmodels.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

  /**
   * factory tells dagger how to provide
   */
  @Binds
  abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

  @Binds
  @IntoMap
  @ViewModelScope(TasksViewModel::class)
  abstract fun bindTasksViewModel(tasksViewModel: TasksViewModel): ViewModel
}