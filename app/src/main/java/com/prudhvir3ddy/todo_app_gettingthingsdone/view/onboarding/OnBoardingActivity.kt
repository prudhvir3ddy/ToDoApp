package com.prudhvir3ddy.todo_app_gettingthingsdone.view.onboarding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.prudhvir3ddy.todo_app_gettingthingsdone.R
import com.prudhvir3ddy.todo_app_gettingthingsdone.ToDoApp
import com.prudhvir3ddy.todo_app_gettingthingsdone.storage.SharedPrefs
import kotlinx.android.synthetic.main.activity_on_boarding.view_pager2
import javax.inject.Inject

class OnBoardingActivity : AppCompatActivity() {

  @Inject
  lateinit var sharedPrefs: SharedPrefs

  override fun onCreate(savedInstanceState: Bundle?) {
    (application as ToDoApp).appComponent.inject(this)
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_on_boarding)

    view_pager2.adapter = OnBoardViewPagerAdapter(sharedPrefs)

  }
}
