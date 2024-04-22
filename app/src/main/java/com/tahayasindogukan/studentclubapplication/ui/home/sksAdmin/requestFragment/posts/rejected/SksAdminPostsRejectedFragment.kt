package com.tahayasindogukan.studentclubapplication.ui.home.sksAdmin.requestFragment.posts.rejected

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tahayasindogukan.studentclubapplication.R


class SksAdminPostsRejectedFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sks_admin_posts_rejected, container, false)
    }

}