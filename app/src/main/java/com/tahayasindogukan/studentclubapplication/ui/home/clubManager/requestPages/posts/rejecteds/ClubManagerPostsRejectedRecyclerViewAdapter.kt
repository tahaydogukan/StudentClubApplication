package com.tahayasindogukan.studentclubapplication.ui.home.clubManager.requestPages.posts.rejecteds

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tahayasindogukan.studentclubapplication.R
import com.tahayasindogukan.studentclubapplication.core.entitiy.Request
import com.tahayasindogukan.studentclubapplication.databinding.RequestCardViewBinding

class ClubManagerPostsRejectedRecyclerViewAdapter(var requestsList: List<Request>,
                                                  private val context: Context,
                                                  val listener:PostsRejectedClickListener) :
    RecyclerView.Adapter<ClubManagerPostsRejectedRecyclerViewAdapter.ClubManagerPostsRejectedViewHolder>() {


    inner class ClubManagerPostsRejectedViewHolder(var view: RequestCardViewBinding) :
        RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClubManagerPostsRejectedViewHolder {
        val binding =
            RequestCardViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ClubManagerPostsRejectedViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return requestsList.size
    }

    //holder sayesinde card tasarımına ulaşıyoruz
    override fun onBindViewHolder(holder: ClubManagerPostsRejectedViewHolder, position: Int) {
        val t = holder.view
        val document = requestsList[position]

        t.requestPhoto.setImageResource(R.drawable.request_icon)
        t.requestName.text = document.title

        t.cardView.setOnClickListener {
            listener.onClick(
                document
            )
        }

    }
    interface PostsRejectedClickListener {
        public fun onClick(
            request: Request
        )
    }
}