package com.tahayasindogukan.studentclubapplication.ui.home.clubManager

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tahayasindogukan.studentclubapplication.R
import com.tahayasindogukan.studentclubapplication.core.entitiy.Activity
import com.tahayasindogukan.studentclubapplication.databinding.SksAdminCalendarFragmentCardViewBinding

class ClubManagerCalendarAdapter(var activtiyList: List<Activity>, val listener: MyClickListener) :
    RecyclerView.Adapter<ClubManagerCalendarAdapter.ClubManagerCalendarViewHolder>() {


    inner class ClubManagerCalendarViewHolder(var view: SksAdminCalendarFragmentCardViewBinding) :
        RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ClubManagerCalendarViewHolder {
        val binding = SksAdminCalendarFragmentCardViewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ClubManagerCalendarViewHolder(binding)
    }

    fun setFilteredList(clubList: List<Activity>) {
        activtiyList = clubList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return activtiyList.size
    }

    //holder sayesinde card tasarımına ulaşıyoruz
    override fun onBindViewHolder(holder: ClubManagerCalendarViewHolder, position: Int) {
        val t = holder.view
        val document = activtiyList[position]

        t.clubPhoto.setImageResource(R.drawable.image_deneme)
        t.requestsCardViewLayout.setOnClickListener {
        t.activityName.text = document.activityYear.toString()
        t.activityMonthAndDate.text = "${document.activityMonth}\n${document.activityDay}"
            listener.onClick(
                document.activityTitle,
                document.activityContent,
                document.activityLocation,
                document.activityManager,
                document.activityAttachment,
                document.activityYear, document.activityMonth, document.activityDay,
                document.activityTags
            )
        }


    }

    interface MyClickListener {
        public fun onClick(
            activityTitle: String,
            activityContent: String, activityLocation: String,
            activityManager: String,
            activityAttachment: String,
            activityYear: String,
            activityMonth: String,
            activityDay: String,
            activityTags: String
        )
    }

}