package com.tahayasindogukan.studentclubapplication.ui.home.clubManager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.tahayasindogukan.studentclubapplication.databinding.FragmentClubManagerHomePageBinding

class ClubManagerHomePageFragment : Fragment() {
    private lateinit var binding: FragmentClubManagerHomePageBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentClubManagerHomePageBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        return binding.root
    }

}