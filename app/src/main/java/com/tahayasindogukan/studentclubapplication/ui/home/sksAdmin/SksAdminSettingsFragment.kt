package com.tahayasindogukan.studentclubapplication.ui.home.sksAdmin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.tahayasindogukan.studentclubapplication.databinding.FragmentSksAdminSettingsBinding


class SksAdminSettingsFragment : Fragment() {
    private lateinit var binding: FragmentSksAdminSettingsBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSksAdminSettingsBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        return binding.root
    }

}