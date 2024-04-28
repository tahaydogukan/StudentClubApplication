package com.tahayasindogukan.studentclubapplication.ui.home.sksAdmin.profileFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.tahayasindogukan.studentclubapplication.databinding.FragmentSksAdminProfileNotificationsBinding


class SksAdminProfileNotificationsFragment : Fragment() {
    private lateinit var binding: FragmentSksAdminProfileNotificationsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            FragmentSksAdminProfileNotificationsBinding.inflate(layoutInflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

}