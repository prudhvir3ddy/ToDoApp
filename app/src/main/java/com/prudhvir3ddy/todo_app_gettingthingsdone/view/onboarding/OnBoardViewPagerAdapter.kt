package com.prudhvir3ddy.todo_app_gettingthingsdone.view.onboarding

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.prudhvir3ddy.todo_app_gettingthingsdone.R
import com.prudhvir3ddy.todo_app_gettingthingsdone.storage.SharedPrefs
import com.prudhvir3ddy.todo_app_gettingthingsdone.view.login.LoginActivity
import kotlinx.android.synthetic.main.item_onboard.view.continue_btn
import kotlinx.android.synthetic.main.item_onboard.view.desc_tv
import kotlinx.android.synthetic.main.item_onboard.view.layoutDots
import kotlinx.android.synthetic.main.item_onboard.view.main_iv
import kotlinx.android.synthetic.main.item_onboard.view.title_tv

class OnBoardViewPagerAdapter(
  private val sharedPrefs: SharedPrefs
) : RecyclerView.Adapter<PagerVH>() {

  data class OnBoard(
    val title: String,
    val description: String,
    val image: Int
  )

  private val data = listOf(
    OnBoard(
      "Pretty Simple ToDo App",
      "We love simplicity just like you. we promise we do what we say.",
      R.drawable.walk
    ),
    OnBoard(
      "Add Your Tasks",
      "Don't let your memory stop your productivity. We will remember them for you with images",
      R.drawable.note_list
    ),
    OnBoard(
      "Mark Them As Done",
      "We know you are not free just mark them as done. we will take care about deleting them when we are free",
      R.drawable.accept_request
    )
  )

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerVH =
    PagerVH(LayoutInflater.from(parent.context).inflate(R.layout.item_onboard, parent, false))

  override fun getItemCount(): Int = data.size

  override fun onBindViewHolder(holder: PagerVH, position: Int) = holder.itemView.run {

    title_tv.text = data[position].title
    desc_tv.text = data[position].description
    Glide.with(this).load(data[position].image).into(main_iv)
    if (position == data.size - 1) {
      continue_btn.visibility = View.VISIBLE
      layoutDots.visibility = View.GONE
    }

    continue_btn.setOnClickListener {
      sharedPrefs.setFirstTime()
      context.startActivity(Intent(context, LoginActivity::class.java))
    }
  }

}

class PagerVH(itemView: View) : RecyclerView.ViewHolder(itemView)
