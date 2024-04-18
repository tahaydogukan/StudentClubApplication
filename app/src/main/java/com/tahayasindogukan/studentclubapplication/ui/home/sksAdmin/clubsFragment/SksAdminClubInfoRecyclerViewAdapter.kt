package com.tahayasindogukan.studentclubapplication.ui.home.sksAdmin.clubsFragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tahayasindogukan.studentclubapplication.R
import com.tahayasindogukan.studentclubapplication.core.entitiy.Request
import com.tahayasindogukan.studentclubapplication.databinding.SksAdminClubInfoCardViewBinding

class SksAdminClubInfoRecyclerViewAdapter(
    var requestList: List<Request>,
    val listener: SksAdminClubInfoClickListener
) :
    RecyclerView.Adapter<SksAdminClubInfoRecyclerViewAdapter.SksAdmınClubInfoViewHolder>() {


    inner class SksAdmınClubInfoViewHolder(var view: SksAdminClubInfoCardViewBinding) :
        RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SksAdmınClubInfoViewHolder {
        val binding =
            SksAdminClubInfoCardViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return SksAdmınClubInfoViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return requestList.size
    }

    //holder sayesinde card tasarımına ulaşıyoruz
    override fun onBindViewHolder(holder: SksAdmınClubInfoViewHolder, position: Int) {
        val t = holder.view
        val document = requestList[position]

        t.sksAdminClubInfoCardViewIwRequestPhoto.setImageResource(R.drawable.image_deneme)
        t.sksAdminClubInfoCardViewTwStartDate.text = document.startDate
        t.sksAdminClubInfoCardViewTwEndDate.text = document.endDate
        t.sksAdminClubInfoCardViewTwTitle.text = document.title

    }

    interface SksAdminClubInfoClickListener {
        fun onClick(
            request: Request
        )
    }

}