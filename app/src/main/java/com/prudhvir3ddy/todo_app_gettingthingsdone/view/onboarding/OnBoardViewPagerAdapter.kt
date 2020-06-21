package com.prudhvir3ddy.todo_app_gettingthingsdone.view.onboarding

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.prudhvir3ddy.todo_app_gettingthingsdone.R
import com.prudhvir3ddy.todo_app_gettingthingsdone.databinding.ItemOnboardBinding
import com.prudhvir3ddy.todo_app_gettingthingsdone.storage.SharedPrefs
import com.prudhvir3ddy.todo_app_gettingthingsdone.view.login.LoginActivity
import org.jetbrains.annotations.NotNull

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
    PagerVH(ItemOnboardBinding.inflate(LayoutInflater.from(parent.context), parent, false))

  override fun getItemCount(): Int = data.size

  override fun onBindViewHolder(holder: PagerVH, position: Int) {

    holder.titleTextView.text = data[position].title
    holder.descriptionTextView.text = data[position].description
    Glide.with(holder.itemView.context).load(data[position].image).into(holder.mainImageView)
    if (position == data.size - 1) {
      holder.continueButton.visibility = View.VISIBLE
      holder.layoutDots.visibility = View.GONE
    }

    holder.continueButton.setOnClickListener {
      sharedPrefs.setFirstTime()
      val intent = Intent(holder.itemView.context, LoginActivity::class.java)
      intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
      holder.itemView.context.startActivity(intent)
    }
  }

}

class PagerVH(itemView: @NotNull ItemOnboardBinding) : RecyclerView.ViewHolder(itemView.root) {
  val titleTextView = itemView.titleTv
  val descriptionTextView = itemView.descTv
  val mainImageView = itemView.mainIv
  val continueButton = itemView.continueBtn
  val layoutDots = itemView.layoutDots

}
