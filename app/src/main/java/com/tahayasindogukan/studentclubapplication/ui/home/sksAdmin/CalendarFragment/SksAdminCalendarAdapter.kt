package com.tahayasindogukan.studentclubapplication.ui.home.sksAdmin.CalendarFragment

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tahayasindogukan.studentclubapplication.R
import com.tahayasindogukan.studentclubapplication.core.entitiy.Activity
import com.tahayasindogukan.studentclubapplication.databinding.SksAdminCalendarFragmentCardViewBinding


class SksAdminCalendarAdapter(var requestsList: List<Activity>,val listener:MyClickListener) :
    RecyclerView.Adapter<SksAdminCalendarAdapter.CalendarViewHolder>() {


    inner class CalendarViewHolder(var view: SksAdminCalendarFragmentCardViewBinding) :
        RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarViewHolder {
        val binding =
            SksAdminCalendarFragmentCardViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return CalendarViewHolder(binding)
    }

    fun setFilteredList(clubList: List<Activity>) {
        this.requestsList = clubList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return requestsList.size
    }

    //holder sayesinde card tasarımına ulaşıyoruz
    override fun onBindViewHolder(holder: CalendarViewHolder, position: Int) {
        val t = holder.view
        //val document = requestsList[position]

        t.clubPhoto.setImageResource(R.drawable.image_deneme)


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