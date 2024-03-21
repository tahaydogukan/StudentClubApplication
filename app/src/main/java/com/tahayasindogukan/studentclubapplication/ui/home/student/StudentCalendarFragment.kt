package com.tahayasindogukan.studentclubapplication.ui.home.student

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.tahayasindogukan.studentclubapplication.databinding.FragmentStudentCalendarBinding

class StudentCalendarFragment : Fragment() {
    private lateinit var binding: FragmentStudentCalendarBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStudentCalendarBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        return binding.root
    }

}