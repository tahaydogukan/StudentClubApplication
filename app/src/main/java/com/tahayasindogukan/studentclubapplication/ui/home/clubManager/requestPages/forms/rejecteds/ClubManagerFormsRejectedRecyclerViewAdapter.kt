package com.tahayasindogukan.studentclubapplication.ui.home.clubManager.requestPages.forms.rejecteds

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tahayasindogukan.studentclubapplication.R
import com.tahayasindogukan.studentclubapplication.core.entitiy.Request
import com.tahayasindogukan.studentclubapplication.databinding.RequestCardViewBinding
import com.tahayasindogukan.studentclubapplication.ui.home.clubManager.requestPages.forms.pendings.ClubManagerFormsPendingRecyclerViewAdapter

class ClubManagerFormsRejectedRecyclerViewAdapter(var requestsList: List<Request>, private val context: Context,val listener: FormsRejectedClickListener) :
    RecyclerView.Adapter<ClubManagerFormsRejectedRecyclerViewAdapter.ClubManagerFormsRejectedViewHolder>() {


    inner class ClubManagerFormsRejectedViewHolder(var view: RequestCardViewBinding) :
        RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClubManagerFormsRejectedViewHolder {
        val binding =
            RequestCardViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ClubManagerFormsRejectedViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return requestsList.size
    }

    //holder sayesinde card tasarımına ulaşıyoruz
    override fun onBindViewHolder(holder: ClubManagerFormsRejectedViewHolder, position: Int) {
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
    interface FormsRejectedClickListener {
        public fun onClick(
            request:Request
        )
    }
}