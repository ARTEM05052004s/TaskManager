package com.example.taskmanager.ui.onboarding.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.taskmanager.databinding.ItemOnboargingBinding
import com.example.taskmanager.model.OnBoarding

class OnBoardingAdapter(private val onClick: () -> Unit) :
    Adapter<OnBoardingAdapter.OnBoardingViewHolder>() {

    val list = arrayListOf(
        OnBoarding(
            "Organise Your Tasks & Projects Easily",
            "Organize your tasks, set deadlines, and prioritize your work",
            "https://i.pinimg.com/564x/7f/9a/a1/7f9aa1de77efa7d3daa30c01f6cef28f.jpg"
        ),
        OnBoarding(
            "Always Connect with Team Anytime Anywhere",
            "Communicate, share updates, and assign tasks in real-time",
            "https://i.pinimg.com/564x/19/28/df/1928df4a37f80e73e435e4f3cc4cf828.jpg"
        ),
        OnBoarding(
            "Everything You Can Do in the App",
            "Create and organize tasks, set reminders, and track your progress - all in one place.",
            "https://i.pinimg.com/564x/d3/f8/86/d3f8863a077c5b54e181193d9a70291b.jpg"
        ),
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardingViewHolder {
        return OnBoardingViewHolder(
            ItemOnboargingBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: OnBoardingViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    inner class OnBoardingViewHolder(private val binding: ItemOnboargingBinding) :
        ViewHolder(binding.root) {
        fun onBind(onBoarding: OnBoarding) {
            binding.tvTitle.text = onBoarding.title
            binding.tvDesc.text = onBoarding.desc
            Glide.with(binding.ivOnboard).load(onBoarding.image).into(binding.ivOnboard)
            binding.btnStart.isVisible = adapterPosition == list.lastIndex
            binding.btnStart.setOnClickListener {
                onClick()
            }
        }
    }
}