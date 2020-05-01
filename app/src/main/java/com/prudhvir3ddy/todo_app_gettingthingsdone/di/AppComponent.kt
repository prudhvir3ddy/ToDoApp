package com.prudhvir3ddy.todo_app_gettingthingsdone.di

import android.content.Context
import com.prudhvir3ddy.todo_app_gettingthingsdone.view.detail.DetailActivity
import com.prudhvir3ddy.todo_app_gettingthingsdone.view.login.LoginActivity
import com.prudhvir3ddy.todo_app_gettingthingsdone.view.main.TasksActivity
import com.prudhvir3ddy.todo_app_gettingthingsdone.view.onboarding.OnBoardingActivity
import com.prudhvir3ddy.todo_app_gettingthingsdone.view.splash.SplashActivity
import com.prudhvir3ddy.todo_app_gettingthingsdone.workmanager.MyWorker
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

/**
 * To generate dagger app component
 * we define all starting points of graph here
 */
@Singleton
@Component(modules = [AppModule::class, ViewModelModule::class])
interface AppComponent {

  /**
   *  Factory to create instances of the AppComponent -
   *  factory tells how to create objects, now we are using bindsinstance of context
   *  so context will be available in dagger graph
   */
  @Component.Factory
  interface Factory {
    /**
     *  With @BindsInstance, the Context passed in will be available in the graph
     */
    fun create(@BindsInstance context: Context): AppComponent
  }

  /**
   * inject all activities and fragments here
   */
  fun inject(tasksActivity: TasksActivity)
  fun inject(splashActivity: SplashActivity)
  fun inject(loginActivity: LoginActivity)
  fun inject(detailActivity: DetailActivity)
  fun inject(onBoardingActivity: OnBoardingActivity)
  fun inject(worker: MyWorker)
}