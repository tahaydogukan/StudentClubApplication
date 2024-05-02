package com.tahayasindogukan.studentclubapplication.ui.home.sksAdmin.profileFragment

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tahayasindogukan.studentclubapplication.core.entitiy.Club
import com.tahayasindogukan.studentclubapplication.core.entitiy.Request
import com.tahayasindogukan.studentclubapplication.databinding.SksAdminNotificationCardViewBinding

class SksAdminNotificationRequestAdapter(
    var requestList: List<Request>,
    private val context: Context,
    val listener: SksAdminEditsClubAdapterClickListener
) :
    RecyclerView.Adapter<SksAdminNotificationRequestAdapter.SksAdminNotificationsAdapterViewHolder>() {


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
        return requestList.size
    }

    //holder sayesinde card tasarımına ulaşıyoruz
    override fun onBindViewHolder(holder: SksAdminNotificationsAdapterViewHolder, position: Int) {
        val t = holder.view
        val document = requestList[position]



        t.notificationCardView.setOnClickListener {
            listener.onClick(
                document
            )
        }
    }

    interface SksAdminEditsClubAdapterClickListener {
        fun onClick(
            request: Request
        )
    }
}