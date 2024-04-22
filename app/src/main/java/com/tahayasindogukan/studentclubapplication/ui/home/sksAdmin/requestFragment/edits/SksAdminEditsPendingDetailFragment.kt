package com.tahayasindogukan.studentclubapplication.ui.home.sksAdmin.requestFragment.edits

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tahayasindogukan.studentclubapplication.R


class SksAdminEditsPendingDetailFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sks_admin_edits_pending_detail, container, false)
    }

}