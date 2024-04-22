package com.tahayasindogukan.studentclubapplication.ui.home.sksAdmin

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tahayasindogukan.studentclubapplication.R
import com.tahayasindogukan.studentclubapplication.core.entitiy.Request
import com.tahayasindogukan.studentclubapplication.databinding.RequestCardViewBinding

class SksAdminRequestAdapter(
    var requestsList: List<Request>,
    private val context: Context,
    val listener:SksAdminRequestAdapterClickListener
) :
    RecyclerView.Adapter<SksAdminRequestAdapter.SksAdminRequestAdapterViewHolder>() {


    inner class SksAdminRequestAdapterViewHolder(
        var view: RequestCardViewBinding
    ) :
        RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): SksAdminRequestAdapterViewHolder {
        val binding =
            RequestCardViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )

        return SksAdminRequestAdapterViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return requestsList.size
    }

    //holder sayesinde card tasarımına ulaşıyoruz
    override fun onBindViewHolder(holder: SksAdminRequestAdapterViewHolder, position: Int) {
        val t = holder.view
        val document = requestsList[position]

        t.requestPhoto.setImageResource(R.drawable.request_icon)
        t.requestName.text = document.title

        t.cardView.setOnClickListener {
            listener.onClick(
                document
            )
        }
    }

    interface SksAdminRequestAdapterClickListener {
        public fun onClick(
            request: Request
        )
    }
}