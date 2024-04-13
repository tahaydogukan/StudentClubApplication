package com.tahayasindogukan.studentclubapplication.ui.home.clubManager.requestPages.posts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tahayasindogukan.studentclubapplication.R


class ClubManagerPostsMainPage : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_club_manager_posts_main_page, container, false)
    }

}