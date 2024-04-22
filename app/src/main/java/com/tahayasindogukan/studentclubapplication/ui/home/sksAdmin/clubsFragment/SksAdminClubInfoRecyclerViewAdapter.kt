package com.tahayasindogukan.studentclubapplication.ui.home.sksAdmin.clubsFragment

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tahayasindogukan.studentclubapplication.R
import com.tahayasindogukan.studentclubapplication.core.entitiy.Request
import com.tahayasindogukan.studentclubapplication.databinding.SksAdminClubInfoCardViewBinding

class SksAdminClubInfoRecyclerViewAdapter(
    var requestList: List<Request>,
    val listener: SksAdminClubInfoClickListener,
    val context: Context
) :
    RecyclerView.Adapter<SksAdminClubInfoRecyclerViewAdapter.SksAdminClubInfoViewHolder>() {


    inner class SksAdminClubInfoViewHolder(var view: SksAdminClubInfoCardViewBinding) :
        RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SksAdminClubInfoViewHolder {
        val binding =
            SksAdminClubInfoCardViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return SksAdminClubInfoViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return requestList.size
    }

    //holder sayesinde card tasarımına ulaşıyoruz
    override fun onBindViewHolder(holder: SksAdminClubInfoViewHolder, position: Int) {
        val t = holder.view
        val document = requestList[position]

        t.sksAdminClubInfoCardViewIwRequestPhoto.setImageResource(R.drawable.image_deneme)
        t.sksAdminClubInfoCardViewTwStartDate.text = document.startDate
        t.sksAdminClubInfoCardViewTwEndDate.text = document.endDate
        t.sksAdminClubInfoCardViewTwTitle.text = document.title

        Glide.with(context).load(document.attachment).into(t.sksAdminClubInfoCardViewIwRequestPhoto)

        t.sksAdminClubInfoCardView.setOnClickListener {
            listener.onClick(
                document
            )
        }

    }

    interface SksAdminClubInfoClickListener {
        fun onClick(
            request: Request
        )
    }

}