package com.prudhvir3ddy.todo_app_gettingthingsdone.di

import androidx.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelScope(val value: KClass<out ViewModel>)