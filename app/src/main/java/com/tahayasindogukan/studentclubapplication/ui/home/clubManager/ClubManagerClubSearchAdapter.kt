package com.tahayasindogukan.studentclubapplication.ui.home.clubManager

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tahayasindogukan.studentclubapplication.core.entitiy.Club
import com.tahayasindogukan.studentclubapplication.databinding.SksAdminClubsFragmentCardViewBinding


class ClubManagerClubSearchAdapter(
    var clubList: List<Club>,
    private val context: Context,
    val listener: MyClickListener
) :
    RecyclerView.Adapter<ClubManagerClubSearchAdapter.ClubManagerClubSearchViewHolder>() {


    inner class ClubManagerClubSearchViewHolder(
        var view: SksAdminClubsFragmentCardViewBinding) :
        RecyclerView.ViewHolder(view.root)


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ClubManagerClubSearchViewHolder {
        val binding =
            SksAdminClubsFragmentCardViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )

        return ClubManagerClubSearchViewHolder(binding)
    }

    fun setFilteredList(clubList: List<Club>) {
        this.clubList = clubList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return clubList.size
    }

    //holder sayesinde card tasarımına ulaşıyoruz
    override fun onBindViewHolder(holder: ClubManagerClubSearchViewHolder, position: Int) {
        val t = holder.view
        val club = clubList[position]

        Glide.with(context).load(club.clubPhoto).centerCrop().into(t.clubPhoto)
        t.clubName.text = club.clubName
        t.cardView.setOnClickListener {
            listener.onClick(
                club.clubName,
                club.clubManagerId,
                club.clubDescription,
                club.clubPhoto
            )
        }

    }

    interface MyClickListener {
        public fun onClick(
            clubName: String,
            clubManager: String,
            clubDescription: String,
            clubPhoto: String
        )
    }
}