package com.tahayasindogukan.studentclubapplication.ui.home.sksAdmin.profileFragment

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tahayasindogukan.studentclubapplication.core.entitiy.Club
import com.tahayasindogukan.studentclubapplication.databinding.SksAdminNotificationCardViewBinding


class SksAdminProfileNotificationsAdapter(
    var clubList: List<Club>,
    private val context: Context,
    val listener: SksAdminEditsClubAdapterClickListener
) :
    RecyclerView.Adapter<SksAdminProfileNotificationsAdapter.SksAdminNotificationsAdapterViewHolder>() {


    inner class SksAdminNotificationsAdapterViewHolder(
        var view: SksAdminNotificationCardViewBinding
    ) :
        RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): SksAdminNotificationsAdapterViewHolder {
        val binding =
            SksAdminNotificationCardViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )

        return SksAdminNotificationsAdapterViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return clubList.size
    }

    //holder sayesinde card tasarımına ulaşıyoruz
    override fun onBindViewHolder(holder: SksAdminNotificationsAdapterViewHolder, position: Int) {
        val t = holder.view
        val document = clubList[position]



        t.notificationCardView.setOnClickListener {
            listener.onClick(
                document
            )
        }
    }

    interface SksAdminEditsClubAdapterClickListener {
        fun onClick(
            club: Club
        )
    }
}