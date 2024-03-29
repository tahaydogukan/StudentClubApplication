package com.tahayasindogukan.studentclubapplication.ui.home.clubManager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.tahayasindogukan.studentclubapplication.databinding.FragmentClubManagerCreatePostBinding

class ClubManagerCreatePostFragment : Fragment() {
    private lateinit var binding: FragmentClubManagerCreatePostBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentClubManagerCreatePostBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        return binding.root
    }

}