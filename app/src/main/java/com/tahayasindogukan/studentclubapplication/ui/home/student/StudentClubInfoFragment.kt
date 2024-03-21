package com.tahayasindogukan.studentclubapplication.ui.home.student

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.tahayasindogukan.studentclubapplication.databinding.FragmentStudentClubInfoBinding

class StudentClubInfoFragment : Fragment() {
    private lateinit var binding: FragmentStudentClubInfoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStudentClubInfoBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        return binding.root
    }

}