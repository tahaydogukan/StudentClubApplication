package com.tahayasindogukan.studentclubapplication.ui.home.sksAdmin.clubsFragment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tahayasindogukan.studentclubapplication.core.entitiy.Club
import com.tahayasindogukan.studentclubapplication.databinding.SksAdminClubsFragmentCardViewBinding

class SksAdminClubSearchAdapter(var clubList: List<Club>) :
    RecyclerView.Adapter<SksAdminClubSearchAdapter.SavedWordsViewHolder>() {


    inner class SavedWordsViewHolder(var view: SksAdminClubsFragmentCardViewBinding) :
        RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SavedWordsViewHolder {
        val binding = SksAdminClubsFragmentCardViewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return SavedWordsViewHolder(binding)
    }

    fun setFilteredList(clubList: List<Club>) {
        this.clubList = clubList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return clubList.size
    }

    //holder sayesinde card tasarımına ulaşıyoruz
    override fun onBindViewHolder(holder: SavedWordsViewHolder, position: Int) {
        val clubList = clubList[position]
        val t = holder.view
        t.textView11.text = clubList.name
    }
}