package com.tahayasindogukan.studentclubapplication.ui.home.sksAdmin

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tahayasindogukan.studentclubapplication.R
import com.tahayasindogukan.studentclubapplication.core.entitiy.Club
import com.tahayasindogukan.studentclubapplication.core.entitiy.Request
import com.tahayasindogukan.studentclubapplication.databinding.RequestCardViewBinding

class SksAdminClubEditsAdapter(
    var clubList: List<Club>,
    private val context: Context,
    val listener:SksAdminEditsClubAdapterClickListener
) :
    RecyclerView.Adapter<SksAdminClubEditsAdapter.SksAdminClubEditsAdapterViewHolder>() {


    inner class SksAdminClubEditsAdapterViewHolder(
        var view: RequestCardViewBinding
    ) :
        RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): SksAdminClubEditsAdapterViewHolder {
        val binding =
            RequestCardViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )

        return SksAdminClubEditsAdapterViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return clubList.size
    }

    //holder sayesinde card tasarımına ulaşıyoruz
    override fun onBindViewHolder(holder: SksAdminClubEditsAdapterViewHolder, position: Int) {
        val t = holder.view
        val document = clubList[position]

        t.requestPhoto.setImageResource(R.drawable.request_icon)
        t.requestName.text = document.clubName

        t.cardView.setOnClickListener {
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