package com.tahayasindogukan.studentclubapplication.ui.home.sksAdmin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.tahayasindogukan.studentclubapplication.databinding.FragmentSksAdminHomePageBinding

class SksAdminHomePageFragment : Fragment() {
    private lateinit var binding: FragmentSksAdminHomePageBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSksAdminHomePageBinding.inflate(inflater, container, false)
        return binding.root
    }

}

