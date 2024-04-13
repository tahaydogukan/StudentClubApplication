package com.tahayasindogukan.studentclubapplication.ui.home.clubManager.requestPages.forms.pendings

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tahayasindogukan.studentclubapplication.R
import com.tahayasindogukan.studentclubapplication.core.entitiy.Request
import com.tahayasindogukan.studentclubapplication.databinding.RequestCardViewBinding
import com.tahayasindogukan.studentclubapplication.ui.home.clubManager.requestPages.forms.approveds.ClubManagerFormsApprovedRecyclerViewAdapter

class ClubManagerFormsPendingRecyclerViewAdapter(var requestsList: List<Request>,
                                                 private val context: Context,
                                                 val listener:FormsPendingClickListener) :
    RecyclerView.Adapter<ClubManagerFormsPendingRecyclerViewAdapter.ClubManagerFormsPendingViewHolder>() {


    inner class ClubManagerFormsPendingViewHolder(var view: RequestCardViewBinding) :
        RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClubManagerFormsPendingViewHolder {
        val binding =
            RequestCardViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ClubManagerFormsPendingViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return requestsList.size
    }

    //holder sayesinde card tasarımına ulaşıyoruz
    override fun onBindViewHolder(holder: ClubManagerFormsPendingViewHolder, position: Int) {
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
    interface FormsPendingClickListener {
        public fun onClick(
            request:Request
        )
    }
}