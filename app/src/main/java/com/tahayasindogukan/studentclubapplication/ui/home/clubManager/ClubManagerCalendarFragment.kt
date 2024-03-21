package com.tahayasindogukan.studentclubapplication.ui.home.clubManager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.tahayasindogukan.studentclubapplication.R
import com.tahayasindogukan.studentclubapplication.databinding.FragmentClubManagerClubBinding

class ClubManagerCalendarFragment : Fragment() {
    private lateinit var binding: FragmentClubManagerClubBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentClubManagerClubBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_club_manager_calendar, container, false)
    }

}