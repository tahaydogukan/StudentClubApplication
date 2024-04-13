package com.tahayasindogukan.studentclubapplication.core.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.DocumentSnapshot
import com.tahayasindogukan.studentclubapplication.R
import com.tahayasindogukan.studentclubapplication.databinding.RequestCardViewBinding


class RequestsAdapter(var requestsList: List<DocumentSnapshot>) :
    RecyclerView.Adapter<RequestsAdapter.SettingsViewHolder>() {


    inner class SettingsViewHolder(var view: RequestCardViewBinding) :
        RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SettingsViewHolder {
        val binding =
            RequestCardViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SettingsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return requestsList.size
    }

    //holder sayesinde card tasarımına ulaşıyoruz
    override fun onBindViewHolder(holder: SettingsViewHolder, position: Int) {
        val t = holder.view
        val document = requestsList[position]


    }

}