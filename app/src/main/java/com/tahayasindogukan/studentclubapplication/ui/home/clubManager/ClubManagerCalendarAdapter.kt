package com.tahayasindogukan.studentclubapplication.ui.home.clubManager

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tahayasindogukan.studentclubapplication.core.entitiy.Request
import com.tahayasindogukan.studentclubapplication.databinding.SksAdminCalendarFragmentCardViewBinding

class ClubManagerCalendarAdapter(
    var activtiyList: List<Request>,
    val listener: MyClickListener,
    val context: Context
) :
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

    fun setFilteredList(clubList: List<Request>) {
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

        Glide.with(context).load(document.attachment).into(t.clubPhoto)
        t.activityName.text = document.title
        t.activityYear.text = document.startDate.substring(document.startDate.length - 4)
        t.activityMonthAndDate.text = document.startDate.substring(0, document.startDate.length - 5)

        t.requestsCardViewLayout.setOnClickListener {
            listener.onClick(
                document
            )
        }


    }

    interface MyClickListener {
        fun onClick(
            request:Request
        )
    }

}