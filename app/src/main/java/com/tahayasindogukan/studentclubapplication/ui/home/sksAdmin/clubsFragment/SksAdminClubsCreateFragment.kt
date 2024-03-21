package com.tahayasindogukan.studentclubapplication.ui.home.sksAdmin.clubsFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.tahayasindogukan.studentclubapplication.databinding.FragmentSksAdminClubsCreateBinding


class SksAdminClubsCreateFragment : Fragment() {
    private lateinit var binding: FragmentSksAdminClubsCreateBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSksAdminClubsCreateBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        return binding.root
    }

}